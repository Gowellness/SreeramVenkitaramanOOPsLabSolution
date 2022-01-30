import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

class Employee {
    private String firstName;
    private String lastName;
    private String department;
   
    Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
   
    public void setDepartment(String department) {
        this.department = department;
    }
   
    public String getDepartment() {
        return this.department;
    }
   
    public String getFirstName() {
        return this.firstName;
    }
   
    public String getLastName() {
        return this.lastName;
    }
}

class CredentialService {
    Employee e;
   
    CredentialService(Employee e) {
        this.e = e;
    }
   
    public String generatePassword(int length) {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        char[] password = new char[length];
       
        password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        password[3] = numbers.charAt(random.nextInt(numbers.length()));
       
        for(int i = 4; i< length ; i++) {
            password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        return new String(password);
    }
   
    public String generateEmailAddress() {
        return String.format("%s%s@%s.abc.com", e.getFirstName().toLowerCase(), e.getLastName().toLowerCase(), e.getDepartment().substring(0, 4).toLowerCase());
    }
   
    public void showCredentials () {
        System.out.println(String.format("%s -------> %s", "Email", this.generateEmailAddress()));
        System.out.println(String.format("%s -------> %s", "Password", new String(this.generatePassword(8))));
    }
}

public class MyClass {
   
    public static String[] departments = {"Technical", "Admin", "Human Resource", "Legal"};
   
    public static void main(String args[]) throws IOException {
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
       
       
        System.out.println("Please enter your First name:");
        String firstName = reader.readLine();
       
        System.out.println("Please enter your Last name:");
        String lastName = reader.readLine();
       
        Employee e = new Employee(firstName, lastName);
       
        System.out.println("Please enter the department from the following:");
        for(int i = 0; i < departments.length ; i++) {
            System.out.println(String.format("%s: %s", i+1, departments[i]));
        }
        int deptId = Integer.parseInt(reader.readLine());
        e.setDepartment(departments[deptId - 1]);
       
        CredentialService credentialService = new CredentialService(e);
        System.out.println(String.format("Dear %s your generated credentials are as follows:", e.getFirstName()));
        credentialService.showCredentials();
       
    }
}