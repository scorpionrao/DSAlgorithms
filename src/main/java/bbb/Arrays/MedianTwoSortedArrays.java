package bbb.Arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MedianTwoSortedArrays {

    /* Time - O(m+n log(m+n)), Space - O(m+n) */
    public static double findMedianSortedArraysApproach1(int[] A, int[] B) {
        List<Integer> list = new ArrayList<>();
        for(int a : A) {
            list.add(a);
        }
        for(int b : B) {
            list.add(b);
        }
        Collections.sort(list);

        int mid1 = list.size() / 2;
        int mid2 = mid1 - 1;
        if(list.size() % 2 == 0) {
            return ((double) (list.get(mid1) + list.get(mid2))) / 2;
        } else {
            return list.get(mid1);
        }
    }

    /* Time - O(m+n), Space - O(m+n) */
    public static double findMedianSortedArraysApproach2(int[] A, int[] B) {
        int[] sorted = new int[A.length + B.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while(i < A.length && j < B.length) {
            if(A[i] <= B[j]) {
                sorted[k++] = A[i++];
            } else {
                sorted[k++] = B[j++];
            }
        }
        if(i < A.length) {
            sorted[k++] = A[i++];
        }
        if(j < B.length) {
            sorted[k++] = A[j++];
        }
        if(sorted.length % 2 == 0) {
            int mid = sorted.length / 2;
            return ((double) (sorted[mid-1] + sorted[mid])) / 2;
        } else {
            return (double) sorted[sorted.length/2];
        }
    }

    /* Time - O(m+n), Space - O(1) */
    public static double findMedianSortedArraysApproach3(int[] A, int[] B) {

        int mid = 0;
        int prev = 0;
        int i = 0;
        int j = 0;
        int k = 0;
        while(i < A.length && j < B.length) {
            if(A[i] <= B[j]) {
                prev = mid;
                mid = A[i];
                i++;
                k++;
            } else {
                prev = mid;
                mid = B[j];
                j++;
                k++;
            }
            if(k == (A.length + B.length) / 2) {
                break;
            }
        }

        if(k % 2 == 0) {
            return ((double) (mid + prev)) / 2;
        } else {
            return (double) mid;
        }
    }

    /* Time - O(log(min(m,n))), Space - O(1) */
    public static double findMedianSortedArraysApproach4(int[] A, int[] B) {
        if(A.length > B.length) {
            return findMedianSortedArraysApproach4(B, A);
        }
        int bSearchMinIndex = 0;
        int bSearchMaxIndex = A.length;
        int totalLength = A.length + B.length;
        int halfLength = (totalLength + 1) / 2;

        int aCut = 0, bCut = 0;
        while(bSearchMinIndex <= bSearchMaxIndex) {
            aCut = (bSearchMinIndex + bSearchMaxIndex) / 2;
            bCut = halfLength - aCut;
            if(A[aCut-1] < B[bCut] && B[bCut-1] < A[aCut]) {
                // Perfect Scenario
                int maxLeft = 0;
                if (aCut == 0) {
                    // all elements in A are lesser than the B cut
                    maxLeft = B[bCut-1];
                } else if (bCut == 0) {
                    // all elements in B are lesser than the A cut
                    maxLeft = A[aCut-1];
                } else {
                    maxLeft = Math.max(A[aCut - 1], B[bCut - 1]);
                }
                if((totalLength) % 2 == 1) {
                    return (double) maxLeft;
                }

                int minRight = 0;
                if (aCut == A.length) {
                    // all elements in A are lesser than B
                    minRight = B[bCut];
                } else if (bCut == B.length) {
                    // all elements in B are lesser than A
                    minRight = A[aCut];
                } else {
                    minRight = Math.min(A[aCut], B[bCut]);
                }

                return (maxLeft + minRight) / 2.0;

            } else if(A[aCut-1] > B[bCut]) {
                bSearchMaxIndex = aCut - 1;
            } else {
                bSearchMinIndex = aCut + 1;
            }
        }
        return 0.0;
    }

    public static void evaluate(int[] A, int[] B) {
        System.out.println("Approach1 = " + findMedianSortedArraysApproach1(A, B));
        System.out.println("Approach2 = " + findMedianSortedArraysApproach2(A, B));
        System.out.println("Approach3 = " + findMedianSortedArraysApproach3(A, B));
        System.out.println("Approach4 = " + findMedianSortedArraysApproach4(A, B));
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 3, 4, 5};
        int[] B = {1, 3, 5, 7, 9};

        evaluate(A, B);
    }
}
