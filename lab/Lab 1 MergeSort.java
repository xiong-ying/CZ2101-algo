
public class MergeSort {
	
	// Merge two subarrays L and M into array, the most important part
	// 3 pointers = one for each of the two arrays and one for maintaining the current index of the final sorted array.
	//i maintains current index of L, starting at 1
	//j maintains current index of M, starting at 1
	//k maintains the current index of A[p..q], starting at p.
	
	void merge(int arr[], int p, int q, int r) {
		// Create L ← A[p..q] and M ← A[q+1..r]
	    int n1 = q - p + 1;
	    int n2 = r - q;

	    int L[] = new int[n1];
	    int M[] = new int[n2];

	    for (int i = 0; i < n1; i++) {
	      L[i] = arr[p + i];
	    }
	    
	    for (int j = 0; j < n2; j++) {
	      M[j] = arr[q + 1 + j];
	    }

	    // Maintain current index of sub-arrays and main array
	    int i = 0;
	    int j = 0;
	    int k = p;

	    // Until we reach either end of either L or M, pick larger among
	    // elements L and M and place them in the correct position at A[p..r]
	    while (i < n1 && j < n2) {
	      if (L[i] <= M[j]) {
	        arr[k] = L[i];
	        i++;
	      } else {
	        arr[k] = M[j];
	        j++;
	      }
	      k++;
	    }

	    // When we run out of elements in either L or M,
	    // pick up the remaining elements and put in A[p..r]
	    while (i < n1) {
	      arr[k] = L[i];
	      i++;
	      k++;
	    }

	    while (j < n2) {
	      arr[k] = M[j];
	      j++;
	      k++;
	    }
	  }
	

	// Divide the array by recursively breaking down the list/array into two halves until it cannot be divided
	void mergeSort(int arr[], int l, int r) {
		if (l < r) {
			// mid is the point where the array is divided into two subarrays
			int mid = (l + r) / 2;
			
			mergeSort(arr, l, mid);
			mergeSort(arr, mid + 1, r);
			
			// Merge the sorted subarrays
			merge(arr, l, mid, r);
		}
	 }

	// Print the sorted array
	static void printArray(int arr[]) {
		int n = arr.length;
		
	    for (int i = 0; i < n; ++i) {
	      System.out.print(arr[i] + " ");
	    }
	    System.out.println();
	  }
	  

	  // Driver program
	  public static void main(String args[]) {
	    //int inputArry[] = { 6, 5, 12, 10, 9, 1, 158, 5896, 222, 555 };
		  
		  int[] inputArry = new int[10];
		  for(int i = 0; i <  inputArry.length; i++) {
			  inputArry[i] = (int)(Math.random() * 100) + 1;
		  }

	    MergeSort ob = new MergeSort(); //instantiation
	    ob.mergeSort(inputArry, 0, inputArry.length - 1);

	    System.out.println("Sorted array:");
	    printArray(inputArry);
	  }
}
