This Java project demonstrates a multi-threaded approach to the classic Merge Sort algorithm. Using Java's concurrency utilities, such as CountDownLatch and synchronized, this program sorts an array of integers entered by the user. The code highlights efficient sorting through multithreading and synchronization, showcasing Java's Runnable interface and thread management features.

Features
Multi-threaded Sorting: Uses multiple threads to sort an array, optimizing performance through parallel processing.
Synchronization: Ensures thread-safe operations on shared resources during sorting.
User Input: Accepts 6 integers from the user, sorts them, and displays both the original and sorted arrays.
Modular Design: Separate classes handle sorting and merging, making the code easy to extend or modify.
Getting Started
Prerequisites
Java 8 or higher installed.
Git for cloning the repository (optional).
Running the Program
Clone the Repository (optional):

bash
Copy code
git clone https://github.com/yourusername/MultithreadedMergeSort.git
cd MultithreadedMergeSort
Compile and Run:

bash
Copy code
javac SortIntegerImp.java
java SortIntegerImp
Input Numbers:

After running the program, you will be prompted to enter 6 integers.
Enter each integer followed by pressing Enter.
Code Overview
Main Class: SortIntegerImp
User Input: Takes 6 integers as input from the user.
Thread Initialization: Starts a new thread to perform merge sort on the input array.
Countdown Latch: Uses a CountDownLatch to wait until the sorting is completed, ensuring main thread synchronization.
Helper Class: MergeSorts
Implements Runnable, allowing multi-threaded sorting operations.
Recursive Merge Sort: Splits the array into left and right halves, then sorts each half in a separate thread.
Synchronization: Ensures thread-safe operations when accessing shared resources.
Merge Method: Combines and sorts two halves of the array, producing a fully sorted result.
Key Classes and Concepts
CountDownLatch: Used to synchronize the completion of threads. It ensures that the main program waits until the sorting thread completes.
synchronized Keyword: Protects shared resources from concurrent access issues by limiting access to one thread at a time.
Divide and Conquer: Implements the merge sort algorithm's divide-and-conquer approach to recursively split and merge arrays.
Example Output
Here's what you can expect when running the program:

mathematica
Copy code
Enter 6 numbers to be sorted:
Enter Number 1: 34
Enter Number 2: 12
Enter Number 3: 45
Enter Number 4: 32
Enter Number 5: 8
Enter Number 6: 23
Original array: [34, 12, 45, 32, 8, 23]
Thread-0 is splitting [34, 12, 45, 32, 8, 23]
Thread-1 is merging and sorting [12, 34] with [8, 23, 32, 45]
Sorted array: [8, 12, 23, 32, 34, 45]
Contributing
Feel free to fork this repository, make enhancements, and create pull requests. Contributions are welcome!
