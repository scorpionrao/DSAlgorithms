package live;

import java.util.*;

public class AmazonProducts {
	
	static class Product {
		int productId;
		String productName;
		Product(int productId, String productName) {
			this.productId = productId;
			this.productName = productName;
		}

		@Override
		public int hashCode() {
			return this.productId;
		}

		@Override
		public boolean equals(Object other) {
			if(this == other) {return true;}

			if(other == null) {return false;}

			if(getClass() != other.getClass()) {
				return false;
			}

			return this.productId == ((Product) other).productId;
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
	
	static class ProductRetriever {
		
		Set<Product> bfsIterative(Category category) {
			Set<Product> resultDS = new HashSet<>();
			Queue<Category> queue = new LinkedList<>();
			queue.offer(category);
			while(!queue.isEmpty()) {
				Category cat = queue.poll();
                // main category added first
				resultDS.addAll(cat.products);
                // first level child added next
				for(Category subCategory : cat.subCategories) {
					queue.offer(subCategory);
				}
			}
			return resultDS;
		}
		
		Set<Product> dfsIterative(Category category) {
			Set<Product> resultDS = new HashSet<>();
			Stack<Category> stack = new Stack<>();
			stack.push(category);
			while(!stack.isEmpty()) {
                Category cat = stack.pop();
                for(Category subCategory : cat.subCategories) {
                    stack.push(subCategory);
                }
                resultDS.addAll(cat.products);
            }
			return resultDS;
		}

        Set<Product> dfsRecursive(Category category) {
			Set<Product> dataStructure = new LinkedHashSet<>();
			dfsRecursive(category, dataStructure);
			return dataStructure;
		}
	
		void dfsRecursive(Category category, Set<Product> allProducts) {
			if(category == null) {
				return;
			}
			allProducts.addAll(category.products);
            for(Category subCategory : category.subCategories) {
                dfsRecursive(subCategory, allProducts);
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
		retriever.dfsIterative(electronics);
		retriever.dfsIterative(electronics);
		retriever.dfsRecursive(electronics);
		retriever.bfsIterative(electronics);
		
	}

}