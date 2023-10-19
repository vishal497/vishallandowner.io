import java.util.*;
import java.util.stream.Collectors;

class LandPurchase {
    String name;
    String parent;
    int land;

    public LandPurchase(String name, String parent, int land) {
        this.name = name;
        this.parent = parent;
        this.land = land;
    }
}

public class LandRecords {
    public static void main(String[] args) {
        List<LandPurchase> purchases = new ArrayList<>();
        Map<String, String> familyTree = new HashMap<>();

        //store them in the data structure
        purchases.add(new LandPurchase("Arun Kumar", "Suraj Kumar", 10));
        purchases.add(new LandPurchase("Amit Kumar", "Arun Kumar", 15));
        purchases.add(new LandPurchase("Ram Kumar", "Arun Kumar", 20));
        purchases.add(new LandPurchase("Anjali Devi", "Amit Kumar", 12));
        purchases.add(new LandPurchase("Manish Kumar", "Anjali Devi", 17));
        purchases.add(new LandPurchase("Amit Kumar", "Arun Kumar", -10)); 
        purchases.add(new LandPurchase("Manish Kumar", "Amit Kumar", 20));
        purchases.add(new LandPurchase("Ram Kumar", "Arun Kumar", -10));
        purchases.add(new LandPurchase("Priya", "Anjali Devi", 20));

        
        for (LandPurchase purchase : purchases) {
            if (!purchase.parent.isEmpty()) {
                familyTree.put(purchase.name, purchase.parent);
            }
        }

        // cases
        testQueryByName("Arun Kumar", purchases); 
        testQueryByName("Anjali Devi", purchases);  
        testFamilyTree("Amit Kumar", familyTree, purchases);  
        testFamilyTree("Suraj Kumar", familyTree, purchases);  
    }

    //calculate total land 
    private static int calculateLandByName(String name, List<LandPurchase> purchases) {
        return purchases.stream()
                .filter(purchase -> name.equals(purchase.name))
                .mapToInt(purchase -> purchase.land)
                .sum();
    }

    
    private static int calculateLandByFamilyTree(String rootName, Map<String, String> familyTree, List<LandPurchase> purchases) {
        Set<String> descendants = new HashSet<>();
        descendants.add(rootName);

        // Traverse
        for (Map.Entry<String, String> entry : familyTree.entrySet()) {
            if (entry.getValue().equals(rootName)) {
                descendants.add(entry.getKey());
            }
        }

       
        return purchases.stream()
                .filter(purchase -> descendants.contains(purchase.name))
                .mapToInt(purchase -> purchase.land)
                .sum();
    }

   
    private static void testQueryByName(String name, List<LandPurchase> purchases) {
        int landOwned = calculateLandByName(name, purchases);
        System.out.println("owned total land by " + name + ": " + landOwned + " acres");
    }

   
    private static void testFamilyTree(String name, Map<String, String> familyTree, List<LandPurchase> purchases) {
        int landOwned = calculateLandByFamilyTree(name, familyTree, purchases);
        System.out.println("Total land owned by the family of " + name + ": " + landOwned + " acres");
    }
}
