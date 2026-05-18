package persistence;

import core.Book;
import core.Person;
import core.User;
import persistence.dao.Loan;
import persistence.dao.LoanDAOimpl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        LoanDAOimpl loanDAOimpl = new LoanDAOimpl();

        System.out.println( "one loan by id: ");
        System.out.println("introduce an id: ");
        int id = sc.nextInt();

        if(id <=3 && id >=1){
            System.out.println(loanDAOimpl.readLoanById(id));
        }
        else{
            System.out.println("id is not correct. introduce id between 1 and 3.");
        }

        System.out.println("all loans: ");
        for(Loan loan : loanDAOimpl.readAllLoans()){
            System.out.println(loan);
        }
/*

                        System.out.println("creating a loan : ");
                        System.out.println("create a book first ...");


                        Book book = null;
                        System.out.println("book id: ");
                        int bookId =sc.nextInt(); sc.nextLine();
                        System.out.println("book title : ");

                        String title = sc.nextLine();
                                                System.out.println("book author: ");

                        String author = sc.nextLine();
                                                System.out.println("book genre: ");

                        String genre = sc.nextLine();
                        System.out.println("is available? 1 = yes, 0 = no. ");

                        int available = sc.nextInt(); sc.nextLine();

                        if(available != 1 && available != 0){
                            System.out.println("available has to be 1 for available or 0 for not available.");
                        }
                        else{
                               book = new Book(bookId, title, author, genre, available);

                            System.out.println("create a person next ...");
                            System.out.println("mail: ");
                            String mail = sc.nextLine();
                            System.out.println("name: ");
                            String name = sc.nextLine();
                            System.out.println("dni: ");
                            String dni = sc.nextLine();
                            System.out.println("password: ");

                            String password = sc.nextLine();
                            Person person = new User(mail, name, dni, password);

                            System.out.println("now, introduce loan's dates: ");

                            System.out.println("introduce year, month and day in type int value for a loan date");
                           System.out.println("now, introduce loan's dates: ");

                            System.out.println("introduce year, month and day in type int value for a loan date (ex: 2026 05 18):");
                            int year = sc.nextInt();
                            int month = sc.nextInt();
                            int day = sc.nextInt();
                            String loanDateFormatted = String.format("%04d-%02d-%02d", year, month, day);

                            System.out.println("introduce year, month and day in type int value for a due date:");
                            int year1 = sc.nextInt();
                            int month1 = sc.nextInt();
                            int day1 = sc.nextInt();
                            String dueDateFormatted = String.format("%04d-%02d-%02d", year1, month1, day1);

                            System.out.println("introduce year, month and day in type int value for a return date (or 0 0 0 if not returned):");
                            int year2 = sc.nextInt();
                            int month2 = sc.nextInt();
                            int day2 = sc.nextInt();

                            String returnDateFormatted = null;
                            if (year2 != 0) {
                                returnDateFormatted = String.format("%04d-%02d-%02d", year2, month2, day2);
                            }
                            sc.nextLine();
                            System.out.println(loanDAOimpl.createLoan(book, person, loanDateFormatted, dueDateFormatted, returnDateFormatted));
                        }
*/

                        System.out.println("read changes after creation: ");
                        for (Loan loan : loanDAOimpl.readAllLoans()) {
                            System.out.println(loan);
                        }




    }




}
