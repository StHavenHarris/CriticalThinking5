import java.util.Scanner;

public class ProductCalc {
    public static void main(String[] args) {
        //Get user input
        Scanner scanner = new Scanner(System.in);
        int[] number = new int[5];

        //Print message and iterate through the array.
        System.out.println("Enter 5 numbers: ");
        for (int i = 0; i < 5; i++){
            System.out.print("Enter number " + (i + 1) + ": ");
            number[i] = scanner.nextInt();
        }
        //Get the product of all five numbers by calling the method below
        long product = productOfNums(number, 0);
        System.out.println("The product of each number is: " + product);

        scanner.close();
    }

    /**
     * Calculate the product of all five numbers that have been entered.
     * @param arr The array for the five numbers
     * @param index The current element the array is on
     * @return Returns the product of all 5 numbers.
     */
    public static long productOfNums(int[] arr, int index){

        if(index == arr.length){
            return 1;
        }
        return (long) arr[index] * productOfNums(arr, index + 1);
    }


}

