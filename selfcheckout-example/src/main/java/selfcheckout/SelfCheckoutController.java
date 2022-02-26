package selfcheckout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;

public class SelfCheckoutController {

    private SelfCheckout selfCheckout;
    private List<Item> selectableItemsList;

    @FXML
    public Button login, adminMode, deleteItem, backButton, checkout;

    @FXML
    public TextArea checkoutText;

    @FXML
    public TextField phoneNumber;

    @FXML
    public GridPane itemGrid;

    @FXML
    public ListView<String> scannedItemsList;

    @FXML
    public void initialize() {
        initializeSelectableItemsList();
        initializeSelfCheckout();
        updateCartDisplay();

        for (int i = 0; i < selectableItemsList.size(); i++) {
            Item selectableItem = selectableItemsList.get(i);
            itemGrid.add(createItemButton(selectableItem), i % 4, i / 4);
        }
    }

    private void initializeSelfCheckout() {
        String day = LocalDate.now().getDayOfWeek().name().substring(0, 3).toLowerCase();
        List<Campaign> discounts = List.of(
                new Campaign("Helgerabatt på taco", 0.3, "taco", true, List.of("fri", "sat")),
                new Campaign("Mandagsmat til under 200-lappen", 0.25, "dinner", false, List.of("mon")),
                new Campaign("Tilbuds-Torsdag", 0.1, null, true, List.of("thu")),
                new Campaign("Medlemsrabatt", 0.02, null, true,
                        List.of("mon", "tue", "wed", "thu", "fri", "sat", "sun"))

        );
        selfCheckout = new SelfCheckout(day, "test123", discounts);
    }

    @FXML
    public void handleLogin() {
        selfCheckout.registerPhoneNumber(phoneNumber.getText());
        updateCartDisplay();
        phoneNumber.disableProperty().set(true);
        login.disableProperty().set(true);
    }

    @FXML
    public void handleCheckout() {
        System.out.println(selfCheckout);
        initializeSelfCheckout();
        updateCartDisplay();
        phoneNumber.disableProperty().set(false);
        login.disableProperty().set(false);
    }

    @FXML
    public void handleAdminActivation() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Admin login");
        dialog.setHeaderText("Skriv inn admin-passord for denne enheten for å fortsette");
        dialog.setContentText("Passord:");
        String password = dialog.showAndWait().get();
        if (password != null) {
            selfCheckout.activateAdminMode(password);
            adminMode.visibleProperty().set(false);
            backButton.visibleProperty().set(true);
            deleteItem.visibleProperty().set(true);
        }
    }

    @FXML
    public void handleAdminDeactivation() {
        selfCheckout.deactivateAdminMode();
        adminMode.visibleProperty().set(true);
        backButton.visibleProperty().set(false);
        deleteItem.visibleProperty().set(false);
    }

    @FXML
    public void handleItemDeletion() {
        int selectedIndex = scannedItemsList.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            selfCheckout.removeFromCart(selectedIndex);
            updateCartDisplay();
        } else {
            throw new IllegalArgumentException();
        }

    }

    private Button createItemButton(Item item) {
        Button button = new Button(item.toString());
        button.setOnAction((event) -> {
            handleItemSelect(item);
        });
        button.setMaxHeight(Double.MAX_VALUE);
        button.setMaxWidth(Double.MAX_VALUE);
        return button;
    }

    private void handleItemSelect(Item item) {
        selfCheckout.scanItem(item);
        updateCartDisplay();
    }

    private void updateCartDisplay() {
        updateItemList();
        updateCheckoutText();
    }

    private void updateItemList() {
        scannedItemsList.getItems().setAll(selfCheckout.getCartDisplayList());
    }

    private void updateCheckoutText() {
        checkoutText.setText(selfCheckout.getCheckoutText());
    }

    private void initializeSelectableItemsList() {
        selectableItemsList = new ArrayList<>();
        selectableItemsList.addAll(List.of(
                new Item("Tomat", 5.0, "fruit"),
                new Item("Hvitost, Norvegia", 110.0, "diary"),
                new Item("Hvitost, Synnøve", 90.0, "diary"),
                new Item("Tortillalefser", 15.0, "taco"),
                new Item("Kjøttdeig, 400g", 29.99, "taco"),
                new Item("Egg, 6 stk", 41.59, "breakfast"),
                new Item("Leverpostei", 29.99, "breakfast"),
                new Item("Brød", 18.90, "breakfast"),
                new Item("Yoghurt", 15.50, "diary"),
                new Item("Fusilli", 25.0, "dinner"),
                new Item("Dolmio Classico", 40.0, "dinner"),
                new Item("Laksefilet, 500g", 107.0, "dinner"),
                new Item("Pose", 1.50, "general")));

    }

}
