class HelloWorld {
    public static void main(String[] args) {
        for(int i = 3; i >= 0; i--){
            System.out.println("Cik Aivaru ir klase: "+i);
            if (i>1) {
                System.out.println("Aivars iziet no klases");
            }
            if (i==0){
                System.out.println("Skolotaja paliek dusmiga");
            }
        }
    }
}