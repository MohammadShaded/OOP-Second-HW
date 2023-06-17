import java.util.*;

interface PerformanceManipulator {
     void increasePerformanceRating();

}



abstract public class Employee implements PerformanceManipulator {
    private String name;
    private int id;
    private double salary;
    private int performanceRating ;

    public Employee(String name,int id,int per){
        this.performanceRating=per;
        this.name=name;
        this.id=id;
    }
    abstract public double calculateSalary();
    public int getId(){return id;}
    public String getName(){return name;}
    public int getPerformanceRating(){return performanceRating;}

    public void setPerformanceRating(int performanceRating){this.performanceRating=performanceRating;}

    @Override
    public void increasePerformanceRating() {
        performanceRating++;
    }
    @Override
    public String toString() {
        return ("\nName is :"+getName()+"      ID is :"+getId()+"      PerformanceRating is :"+getPerformanceRating()+"      Salary is :"+calculateSalary());
    }
}

class FullTimeEmployee extends Employee{

    final private double baseSalary=50000;
    public FullTimeEmployee(String name,int id,int per){super(name,id,per);}
    public double calculateSalary(){ return baseSalary+((getPerformanceRating()*0.05*baseSalary)>10000?10000:((getPerformanceRating()*0.05*baseSalary)<500?500:(getPerformanceRating()*0.05*baseSalary)));}
}
class PartTimeEmployee extends Employee{
    final private double baseSalary=25000;
    public PartTimeEmployee(String name,int id,int per){super(name,id,per);}
    public double calculateSalary(){ return baseSalary+((getPerformanceRating()*0.05*baseSalary)>2500?2500:((getPerformanceRating()*0.05*baseSalary)<250?250:(getPerformanceRating()*0.05*baseSalary)));}
}


class ContractWorker extends Employee{

    final private double hourlyRate=50;
    private double hoursWorked;
    public ContractWorker(String name, int id, int per,int i){super(name,id,per);
        hoursWorked=i;}
    public double calculateSalary(){return (hoursWorked*hourlyRate);}

}

class Company {
    private int contractCount=0;

    public int getCount() {return contractCount;}

    private ArrayList<Employee> employees=new ArrayList<Employee>() ;
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }
    public void removeEmployee(int id){

      employees.remove(findEmployeeByID( id));

//        for (int i=0; i<employees.size();i++){
//            if(employees.get(i).getId()==id){
//                employees.remove(i);

//
//        }

    }

    public Employee findEmployeeByID(int id){

        for (int i=0; i<employees.size();i++){
            if(employees.get(i).getId()==id){
                return employees.get(i);

            }

        }
        return null;
    }


   public  ArrayList<Employee> getEmployeesSortedByName(){

       ArrayList<Employee>  eList=new ArrayList<Employee>();
       String namList[]=new String [employees.size()];
       //namList= new String[];
       for(int i=0;i<employees.size();i++){
        namList[i]=employees.get(i).getName();
       }
       Arrays.sort(namList);
       for (int i=0;i<namList.length;i++){
       for(int j=0;j<employees.size();j++){
           if (namList[i]==employees.get(j).getName()){
           eList.add(employees.get(j));
           }
       }
       }

       return eList;

   }



   public double calculateTotalSalary(){

        double tot=0;
        for (int i=0; i<employees.size();i++){
            tot+=(employees.get(i).calculateSalary());
        }
        return tot;
   }
   public double calculateAverageSalary(String type){

        int count=0;
        String className="";
        switch (type){
            case"full-time":className="FullTimeEmployee";break;
            case"part-time":className="PartTimeEmployee";break;
            case"contract":className="ContractWorker";break;

        }
       double tot=0.0;
       for (int i=0; i<employees.size();i++){
           if(employees.get(i).getClass().getName()==className){
           tot+=(employees.get(i).calculateSalary());
           count++;}
       }
       if (className=="ContractWorker")contractCount=count;
       return tot/count;
   }
}