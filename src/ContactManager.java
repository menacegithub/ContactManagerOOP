import java.util.Scanner;

public class ContactManager {
    static Contact[] contactArr = new Contact[10];
    static int currentIndex = 0;

    public void start() {

        boolean b = true;
        while (b) {
            menu();
            int n = getMenuNumber();
            switch (n) {
                case 1:
                    Contact contact = addContact();

                    addToArray(contact);

                    break;
                case 2:
                    printCinntactList();
                    break;
                case 3:
                    String query = getQuery();
                    search(query);
                    break;

                case 4:
                    String phone = deleteContact();
                    deleteCotactFromArray(phone);
                    break;
                case 5:
                    b = false;
                    break;
                default:
                    System.out.println("Please enter a valid number. Mazgi");
            }
        }
        menu();
        String s = new Scanner(System.in).nextLine();
    }

    public boolean isValidContact(Contact contact) {
        if (contact.name == null || contact.name.trim().length() < 3) {
            System.out.println("Contact name is wrong");
            return false;
        }
        if (contact.surname == null || contact.surname.trim().length() < 3) {
            System.out.println("Contact surname is wrong");
            return false;
        }
        if (contact.phone == null || contact.phone.trim().length() != 12 || !contact.phone.startsWith("998")) {
            System.out.println("Contact phone is wrong");
            return false;
        }
        char[] phoneArr = contact.phone.toCharArray();
        for (char ch : phoneArr) {
            if (!Character.isDigit(ch)) {
                System.out.println("Contact phone is wrong");
                return false;
            }
        }
        return true;

    }

    public Contact addContact() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name:");
        String name = sc.nextLine();
        System.out.println("Enter surname:");
        String surname = sc.nextLine();
        System.out.println("Enter phone number:");
        String phone = sc.nextLine();
        Contact contact = new Contact();
        contact.name = name;
        contact.surname = surname;
        contact.phone = phone;
        return contact;
    }

    public void addToArray(Contact contact) {
        if (!isValidContact(contact)) {
            return;
        }
        if (isPhoneexists(contact.phone)) {
            System.out.println("Phone number already exists mazgi");
            return;
        }

        if (currentIndex == contactArr.length) {
            Contact[] newArr = new Contact[contactArr.length * 2];

            for (int i = 0; i < contactArr.length; i++) {
                newArr[i] = contactArr[i];
            }
            contactArr = newArr;

        }

        contactArr[currentIndex] = contact;
        currentIndex++;
        System.out.println("Contact added");

    }

    public void printCinntactList() {
        for (Contact c : contactArr) {
            if (c != null) {
                System.out.println(c.name + " " + c.surname + " " + c.phone);
            }

        }
    }

    public String getQuery() {
        System.out.println("Enter query:");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void search(String query) {
        query = query.toLowerCase();
        for (Contact c : contactArr) {
            if (c == null) {
                continue;
            }
            if (c.name.toLowerCase().contains(query) || c.surname.toLowerCase().
                    contains(query) || c.phone.contains(query)) {
                System.out.println(c.name + " " + c.surname + " " + c.phone);

            }
        }
    }

    public boolean isPhoneexists(String phone) {
        for (Contact c : contactArr) {
            if (c != null && c.phone.equals(phone)) {
                if (c.phone.equals(phone)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String deleteContact() {
        System.out.println("Enter phone :");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void deleteCotactFromArray(String phone) {
        for (int i = 0; i < contactArr.length; i++) {
            Contact c = contactArr[i];
            if (c != null && c.phone.equals(phone)) {
                contactArr[i] = null;
                System.out.println("Contact deleted oka");
                break;
            }

        }
    }

    public void menu() {
        System.out.println("** Menu **");
        System.out.println("1 Add Contact");
        System.out.println("2 Add List");
        System.out.println("3 Search");
        System.out.println("4 Delete ");
        System.out.println("5 Exit ");

    }

    public  int getMenuNumber() {
        System.out.println("Choose an option:");
        Scanner s = new Scanner(System.in);
        return s.nextInt();
    }
    private boolean isPhoneNumberUnique(String phoneNumber) {
        for (Contact contact : contactArr) {
            if (contact.equals(phoneNumber)) {
                return false;
            }
        }
        return true;
    }
}
