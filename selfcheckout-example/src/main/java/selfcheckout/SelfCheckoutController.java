package selfcheckout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class SelfCheckoutController {

    private SelfCheckout selfCheckout;
    private List<Item> selectableItemsList;

    @FXML
    public GridPane itemGrid;

    @FXML
    public TextField phoneNumber;

    @FXML
    public TextArea checkoutText;

    @FXML
    public ListView<Item> scannedItemsList;

    @FXML
    public void initialize() {
        initializeSelectableItemsList();
        String weekday = LocalDate.now().getDayOfWeek().name().substring(0, 3);
        selfCheckout = new SelfCheckout(weekday.toLowerCase(), "test123");
        drawCheckoutText();
        updateItemList();

        for (int i = 0; i < selectableItemsList.size(); i++) {
            Item selectableItem = selectableItemsList.get(i);
            Button button = new Button(selectableItem.getName());
            button.wrapTextProperty().setValue(true);
            button.setStyle("-fx-text-alignment: center;");
            button.setOnAction((event) -> handleItemSelect(selectableItem));
            button.setMaxWidth(Double.MAX_VALUE);
            button.setMaxHeight(Double.MAX_VALUE);
            itemGrid.add(button, i % 4, i / 4);
        }
    }

    @FXML
    private void handleItemSelect(Item item) {
        selfCheckout.scanItem(item);
        updateItemList();
        drawCheckoutText();
    }

    private void updateItemList() {
        scannedItemsList.getItems().setAll(selfCheckout.getShoppingCart());
    }

    @FXML
    public void handleLogin() {
        selfCheckout.registerPhoneNumber(phoneNumber.getText());
        drawCheckoutText();
    }

    public void drawCheckoutText() {
        checkoutText.setText(selfCheckout.getCheckoutText());
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
                        new Item("Pose", 1.50, "general")));
    }

}
