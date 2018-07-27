package live;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class AmazonProducts {
	
	static class Product {
		int productId;
		String productName;
		Product(int productId, String productName) {
			this.productId = productId;
			this.productName = productName;
		}
	}
	
	static class Category {
		int categoryId;
		String categoryName;
		Set<Product> products = new HashSet<>();
		Set<Category> subCategories = new HashSet<>();
		
		Category(int categoryId, String categoryName) {
			this.categoryId = categoryId;
			this.categoryName = categoryName;
		}
		
		void addSubCategory(Category subCategory) {
			this.subCategories.add(subCategory);
		}
		
		void addProduct(Product product) {
			this.products.add(product);
		}
	}
	
	static enum Order {
		PRE_ORDER, POST_ORDER
	}
	
	static class ProductRetriever {
		
		Set<Product> bfsIterative(Category category) {
			Set<Product> allProducts = new LinkedHashSet<>();
			Queue<Category> queue = new LinkedList<>();
			queue.offer(category);
			while(!queue.isEmpty()) {
				Category cat = queue.poll();
				allProducts.addAll(cat.products);
				for(Category subCategory : cat.subCategories) {
					queue.offer(subCategory);
				}
			}
			return allProducts;
		}
		
		Set<Product> dfsIterative(Category category, Order order) {
			Set<Product> dataStructure = new LinkedHashSet<>();
			Stack<Category> stack = new Stack<>();
			stack.push(category);
			while(!stack.isEmpty()) {
				process(stack, dataStructure, order);
			}
			return dataStructure;
		}
		
		void process(Stack<Category> stack, Set<Product> dataStructure, Order order) {
			if(!stack.isEmpty()) {
				Category cat = stack.pop();
				if(Order.PRE_ORDER == order) {
					dataStructure.addAll(cat.products);
					for(Category subCategory : cat.subCategories) {
						stack.push(subCategory);
					}
				} else if (Order.POST_ORDER == order) {
					for(Category subCategory : cat.subCategories) {
						stack.push(subCategory);
					}
					dataStructure.addAll(cat.products);
				}
			}
		}	
		
		Set<Product> dfsRecursive(Category category) {
			Set<Product> dataStructure = new LinkedHashSet<>();
			dfsRecursive(category, dataStructure, Order.PRE_ORDER);
			dataStructure = new LinkedHashSet<>();
			dfsRecursive(category, dataStructure, Order.POST_ORDER);
			return dataStructure;
		}
	
		void dfsRecursive(Category category, Set<Product> allProducts, Order order) {
			if(category == null) {
				return;
			}
			if(Order.PRE_ORDER == order) {
				allProducts.addAll(category.products);
				for(Category subCategory : category.subCategories) {
					dfsRecursive(subCategory, allProducts, order);
				}
			} else if(Order.POST_ORDER == order) {
				for(Category subCategory : category.subCategories) {
					dfsRecursive(subCategory, allProducts, order);
				}
				allProducts.addAll(category.products);
			}
		}
	}
	
	public static void main(String[] args) {
		Category electronics = new Category(1, "Electronics");
		Category tvs = new Category(100, "TVs");
		Category radios = new Category(101, "Radios");
		Category leds = new Category(1000, "LED");
		Category lcds = new Category(1001, "LCD");
		Category smallRadios = new Category(1003, "Small Radios");
		Category largeRadios = new Category(1004, "Large Radios");
		
		Product electronicsProduct = new Product(1, "ElectronicsProduct");
		Product tvsProduct = new Product(100, "TVsProduct");
		Product radiosProduct = new Product(101, "RadiosProduct");
		Product samsungTvLed = new Product(1001, "Samsung TV Led");
		Product lcdssProduct = new Product(1002, "ElectronicsProduct");
		
		
		electronics.addProduct(electronicsProduct);
		electronics.addSubCategory(tvs);
		electronics.addSubCategory(radios);
		
		tvs.addProduct(tvsProduct);
		tvs.addSubCategory(lcds);
		tvs.addSubCategory(leds);
		
		radios.addProduct(radiosProduct);
		radios.addSubCategory(smallRadios);
		radios.addSubCategory(largeRadios);
		
		leds.addProduct(samsungTvLed);
		lcds.addProduct(lcdssProduct);
		
		ProductRetriever retriever = new ProductRetriever();
		retriever.dfsIterative(electronics, Order.PRE_ORDER);
		retriever.dfsIterative(electronics, Order.POST_ORDER);
		retriever.dfsRecursive(electronics);
		retriever.bfsIterative(electronics);
		
	}

}