package Inside;

public class Out {
    private Out(){}
    private static Out getInstance(){
        System.out.println("show");
        return Inside.instance;
    }
      private static class Inside {
        private static Out instance = new Out();
        public void showOut(){
            System.out.println("xx");
        }
    }

}



