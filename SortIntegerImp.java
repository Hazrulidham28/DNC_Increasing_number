import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class SortIntegerImp {
    public static void main(String[] args) throws InterruptedException {
        int[] arr = new int[6];
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter 6 numbers to be sorted:");

        for (int i = 0; i < 6; i++) {
            System.out.print("Enter Number " + (i + 1) + ": ");
            arr[i] = scanner.nextInt();
        }

        System.out.println("Original array: " + Arrays.toString(arr));

        //declare barrier 
        CountDownLatch latch = new CountDownLatch(1); 

        MergeSorts mergeSort = new MergeSorts(arr, latch);
        Thread sortingThread = new Thread(mergeSort);
        
        //start the thread
        sortingThread.start();
        
        // wait for all thread finished
        latch.await(); 

        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}

//use implements runnable instead of extending Threads class
class MergeSorts implements Runnable {
    private int[] arr;
    private CountDownLatch latch;

    public MergeSorts(int[] arr, CountDownLatch latch) {
        this.arr = arr;
        this.latch = latch;
    }

    @Override
    public void run() {
    	
    	//in this algorithm, synchronization is not needed
    	//just to show how to implement synchronization
    	// it is used same concept as lock... to ensure the other thread cannot access the same "places" or memory
    	// to avoid overwritten and inconsitency
    	
        synchronized (arr) {
        	
        	//display what number and thread is being splitted
        	System.out.println(Thread.currentThread().getName() + " is splitting " + Arrays.toString(arr));
            mergeSort(arr);
            
        }

        //check if latch is not null
        if (latch != null) {
        	// tell the processor process is finished
        	//reduce the countdown
            latch.countDown(); 
        }
    }

    public void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        
        //divide the array into left and right
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        //latch for child
        CountDownLatch childLatch = new CountDownLatch(2);

        //declare left and right thread
        Thread leftThread = new Thread(new MergeSorts(left, childLatch));
        Thread rightThread = new Thread(new MergeSorts(right, childLatch));
        
        //start thread
        leftThread.start();
        rightThread.start();

        try {
        	//wait until all the left and right is splitted
        	//wait till countdown is 0
            childLatch.await(); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        
        //merge all the left and right when the processor is done
        merge(arr, left, right);
    }

    //method to merge and sort the splitted number
    private static void merge(int[] arr, int[] left, int[] right) {
    	
    	//get size of array
        int leftSize = left.length;
        int rightSize = right.length;
        int i = 0, j = 0, k = 0;
        
        //display current thread 
        System.out.println(Thread.currentThread().getName() + " is merging and sorting" + Arrays.toString(left) + " with " + Arrays.toString(right));
        
        //just normal sorting process for divide and conquer algorithm for increasing number
        while (i < leftSize && j < rightSize) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < leftSize) {
            arr[k++] = left[i++];
        }

        while (j < rightSize) {
            arr[k++] = right[j++];
        }
    }
}
