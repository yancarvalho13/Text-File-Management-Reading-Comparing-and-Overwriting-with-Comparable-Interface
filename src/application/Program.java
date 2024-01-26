package application;

import entities.Employee;

import java.io.*;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scan = new Scanner(System.in);

        List<Employee> namelist = new ArrayList<>();
        String path = "temp/names.txt";
        String outPutPath = "temp/otput.txt";
        String command = "";
        try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
            String employeeCSV = bf.readLine();
            while (employeeCSV != null) {

                String[] fields = employeeCSV.split(",");

                namelist.add(new Employee(fields[0], Double.parseDouble(fields[1])));
                employeeCSV = bf.readLine();
            }
            String addname = bf.readLine();
            while (!command.equalsIgnoreCase("exit")) {
                try {
                    System.out.println("Wanna add any name ?");
                    command = scan.nextLine();
                    if (command.equalsIgnoreCase("exit")) {
                        break;
                    }

                    String[] info = command.split(", ");
                    namelist.add(new Employee(info[0], Double.parseDouble(info[1])));
                    addname = bf.readLine();


                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid format, please separate name ands salary by a comma. ex: Paul , 5000 ");
                }
            }

            Collections.sort(namelist);

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(outPutPath))) {
                for (Employee e : namelist) {
                    String line = e.getName() + " , R$:" + e.getSalary();
                    bw.write(line);
                    bw.newLine();
                    System.out.println(e.getName() + " , R$:" + e.getSalary());

                }
                System.out.println("Results written to: " + outPutPath);
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }


            scan.close();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}