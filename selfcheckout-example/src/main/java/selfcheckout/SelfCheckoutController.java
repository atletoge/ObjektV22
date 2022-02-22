package selfcheckout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class SelfCheckoutController {

    private SelfCheckout selfCheckout;
    private List<Item> selectableItemsList;

    @FXML
    public Button adminMode, deleteItem, login, backButton;
    @FXML
    public GridPane itemGrid;
    @FXML
    public TextField phoneNumber;
    @FXML
    public TextArea checkoutText;
    @FXML
    public ListView<String> scannedItemsList;

    @FXML
    public void initialize() {
        initializeSelectableItemsList();
        // String weekday = LocalDate.now().getDayOfWeek().name().substring(0, 3);
        String weekday = "mon";
        List<Campaign> discounts = List.of(
                new Campaign("Helgerabatt på taco", 0.3, "taco", true, List.of("fri", "sat")),
                new Campaign("Mandagsmat til under 200-lappen", 0.25, "dinner", false, List.of("mon")),
                new Campaign("Tilbuds-Torsdag", 0.1, null, true, List.of("thu")),
                new Campaign("Medlemsrabatt", 0.02, null, true,
                        List.of("mon", "tue", "wed", "thu", "fri", "sat", "sun")));
        selfCheckout = new SelfCheckout(weekday.toLowerCase(), "test123", discounts);
        updateCartDisplay();

        for (int i = 0; i < selectableItemsList.size(); i++) {
            Item selectableItem = selectableItemsList.get(i);
            itemGrid.add(createItemButton(selectableItem), i % 4, i / 4);
        }
    }

    @FXML
    private void handleAdminActivation() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Admin login");
        dialog.setHeaderText("Skriv inn admin-passord for denne enheten for å fortsette");
        dialog.setContentText("Passord:");
        dialog.setGraphic(new ImageView(this.getClass().getResource("lock_open.png").toString()));
        String password = dialog.showAndWait().get();
        if (password != null) {
            selfCheckout.activateAdminMode(password);
            adminMode.visibleProperty().set(false);
            deleteItem.visibleProperty().set(true);
            backButton.visibleProperty().set(true);
        }
    }

    @FXML
    private void handleAdminDeactivation() {
        selfCheckout.deactivateAdminMode();
        adminMode.visibleProperty().set(true);
        deleteItem.visibleProperty().set(false);
        backButton.visibleProperty().set(false);
        updateCartDisplay();
    }

    @FXML
    private void handleItemSelect(Item item) {
        selfCheckout.scanItem(item);
        updateCartDisplay();
    }

    @FXML
    private void handleItemDeletion() {
        int selectedIndex = scannedItemsList.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            selfCheckout.removeFromCart(selectedIndex);
            updateCartDisplay();
        } else {
            throw new IllegalStateException("No item is selected!");
        }
    }

    @FXML
    public void handleLogin() {
        selfCheckout.registerPhoneNumber(phoneNumber.getText());
        updateCartDisplay();
        phoneNumber.disableProperty().set(true);
        login.disableProperty().set(true);
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

    private Button createItemButton(Item item) {
        Button button = new Button(item.toString());
        button.wrapTextProperty().setValue(true);
        button.setStyle("-fx-text-alignment: center;");
        button.setCursor(Cursor.HAND);
        button.setOnAction((event) -> handleItemSelect(item));
        button.setMaxWidth(Double.MAX_VALUE);
        button.setMaxHeight(Double.MAX_VALUE);
        return button;
    }

    private void initializeSelectableItemsList() {
        selectableItemsList = new ArrayList<>();
        selectableItemsList
                .addAll(List.of(
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