import java.util.*;

public class Main {

    public static void main(String[] args) {


        Company soft=new Company();
        System.out.println("Welcome to the Employee Management System");
        Scanner scanner = new Scanner(System.in);

    while (true) {
        System.out.println("\n-----------------------------------\nPlease choose an option:");
        System.out.println("1. Add an employee");
        System.out.println("2. Remove an employee");
        System.out.println("3. Find an employee by ID");
        System.out.println("4. Print a list of all employees sorted by name");
        System.out.println("5. Calculate the total salary of all employees in the company");
        System.out.println("6. Calculate the average salary of full-time employees");
        System.out.println("7. Calculate the average salary of part-time employees");
        System.out.println("8. Calculate the total cost of contract workers");
        System.out.println("9. Increase employee performance rating");
        System.out.println("0. Quit");

        int choice = scanner.nextInt();
        int secChoice=0;
        Scanner secInput=new Scanner(System.in);
        Scanner namInput = new Scanner(System.in);

        switch (choice) {
            case 1:
                Employee employee=null;
                System.out.println("Select an employee type:\n" +
                        "1. Full-time\n" +
                        "2. Part-time\n" +
                        "3. Contract worker");

                secChoice=secInput.nextInt();

                System.out.println("Enter employee name: ");
                    String name=namInput.nextLine();
                System.out.println("Enter employee ID: ");
               int id =secInput.nextInt();
                System.out.println("Enter employee performance rating: ");
               int per =secInput.nextInt();
                if(secChoice==1)employee=new FullTimeEmployee(name,id,per);
                else if(secChoice==2)employee=new PartTimeEmployee(name,id,per);
                else if(secChoice==3){
                    System.out.println("Enter employee hours Worked: ");
                    int hoursWorked =secInput.nextInt();
                    employee=new ContractWorker(name,id,per,hoursWorked);
                }
                else {System.out.println("input invalid plz try again");continue;}
                System.out.println("Employee added successfully.");
                soft.addEmployee(employee);
                break;
            case 2:
                System.out.println("Enter id");
                secChoice=secInput.nextInt();
                soft.removeEmployee(secChoice);
                break;
            case 3:
                System.out.println("Enter id");
                secChoice=secInput.nextInt();
               System.out.println( soft.findEmployeeByID(secChoice)==null?"\nNo employee has this id":soft.findEmployeeByID(secChoice).toString());
                break;
            case 4:
                System.out.println( soft.getEmployeesSortedByName());
                break;
            case 5:
                System.out.println(soft.calculateTotalSalary());
            break;
            case 6:
                System.out.println( soft.calculateAverageSalary("full-time"));
                break;
            case 7:
                System.out.println( soft.calculateAverageSalary("part-time"));
                break;
            case 8:
                soft.calculateAverageSalary("contract");
               System.out.println (soft.calculateAverageSalary("contract") * soft.getCount());
                break;
            case 9:
                System.out.println("Enter id");
                secChoice=secInput.nextInt();
                if( soft.findEmployeeByID(secChoice)!=null) soft.findEmployeeByID(secChoice).increasePerformanceRating();
                else {System.out.println("\nNo employee has this id");continue;}
                System.out.println("New employee information:\n"+soft.findEmployeeByID(secChoice));
                break;
            case 0:
                System.out.println("Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}
}


