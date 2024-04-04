public class Main {
    public static void main(String[] args) throws Exception {

        Integration integration = new Integration(new PseudoDB());
        Printer printer = new Printer();
        Controller controller = new Controller(integration, printer);
        View view = new View(controller);

        Tests tests = new Tests(controller, integration);
        System.out.println(tests.testAll());
        System.out.println("Allt är dit fel");
        System.out.println("Du är anledningen till att vi fick F");
        System.out.println("Vi hatar dig och din familj");
        System.out.println("Du är en skam för alla oss");
    }
}
