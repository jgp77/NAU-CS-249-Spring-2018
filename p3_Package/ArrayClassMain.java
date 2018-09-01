package p3_Package;

public class ArrayClassMain
{
    public static void main(String[] args)
    {
        ArrayClass arrayClass = new ArrayClass();
        arrayClass.loadUniqueRandoms(15, 10, 50);
        ArrayClass copiedArray = new ArrayClass(arrayClass);

        System.out.println("Original Array:");
        for (int i = 0; i < arrayClass.getCurrentSize(); i++)
        {
            System.out.print(arrayClass.accessItemAt(i) + " ");
        }

        System.out.println("\nMerge Sort:\n");

        arrayClass.runMergeSort();
        for (int i = 0; i < arrayClass.getCurrentSize(); i++)
        {
            System.out.print(arrayClass.accessItemAt(i) + " ");
        }

        arrayClass = copiedArray;
        System.out.println("\nQuick Sort:\n");
        arrayClass.runQuickSort();

        for (int i = 0; i < arrayClass.getCurrentSize(); i++)
        {
            System.out.print(arrayClass.accessItemAt(i) + " ");
        }

        arrayClass = copiedArray;
        System.out.println("\nShell Sort:\n");
        arrayClass.runShellSort();

        for (int i = 0; i < arrayClass.getCurrentSize(); i++)
        {
            System.out.print(arrayClass.accessItemAt(i) + " ");
        }

        arrayClass = copiedArray;
        System.out.println("\nCurrent Capacity (15): " + arrayClass.getCurrentCapacity());
        System.out.println("Current Size (15): " + arrayClass.getCurrentSize());

        System.out.println("Full (True): " + arrayClass.isFull());
        System.out.println("Empty (False): " + arrayClass.isEmpty());

        System.out.println("Resize (True): " + arrayClass.resize(200));
        System.out.println("Resize (False): " + arrayClass.resize(2));

        arrayClass=new ArrayClass(15);
        System.out.println("\nCurrent Capacity (15): " + arrayClass.getCurrentCapacity());
        System.out.println("Current Size (0): " + arrayClass.getCurrentSize());

        System.out.println("Full (False): " + arrayClass.isFull());
        System.out.println("Empty (True): " + arrayClass.isEmpty());

        System.out.println("Resize (True): " + arrayClass.resize(200));
        System.out.println("Resize (False): " + arrayClass.resize(2));

        arrayClass=new ArrayClass(15);
        System.out.println("\nCurrent Capacity (15): " + arrayClass.getCurrentCapacity());
        System.out.println("Current Size (0): " + arrayClass.getCurrentSize());

        System.out.println("Full (False): " + arrayClass.isFull());
        System.out.println("Empty (True): " + arrayClass.isEmpty());

        arrayClass.appendItem(1);
        System.out.println("\nCurrent Capacity (15): " + arrayClass.getCurrentCapacity());
        System.out.println("Empty (false): " + arrayClass.isEmpty());


    }
}
