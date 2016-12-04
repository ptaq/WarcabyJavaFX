package WarcabyFX;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class MainController {

    @FXML
    private Button guzior2;
    @FXML
    private Button guzior4;
    @FXML
    private Button guzior6;
    @FXML
    private Button guzior8;
    @FXML
    private Button guzior9;
    @FXML
    private Button guzior11;
    @FXML
    private Button guzior13;
    @FXML
    private Button guzior15;
    @FXML
    private Button guzior18;
    @FXML
    private Button guzior20;
    @FXML
    private Button guzior22;
    @FXML
    private Button guzior24;
    @FXML
    private Button guzior25;
    @FXML
    private Button guzior27;
    @FXML
    private Button guzior29;
    @FXML
    private Button guzior31;
    @FXML
    private Button guzior34;
    @FXML
    private Button guzior36;
    @FXML
    private Button guzior38;
    @FXML
    private Button guzior40;
    @FXML
    private Button guzior41;
    @FXML
    private Button guzior43;
    @FXML
    private Button guzior45;
    @FXML
    private Button guzior47;
    @FXML
    private Button guzior50;
    @FXML
    private Button guzior52;
    @FXML
    private Button guzior54;
    @FXML
    private Button guzior56;
    @FXML
    private Button guzior57;
    @FXML
    private Button guzior59;
    @FXML
    private Button guzior61;
    @FXML
    private Button guzior63;

    @FXML
    private Button remis;

    @FXML
    private Label label;

    private ObservableList<Button> lista;

    static final Image CZARNYP = new Image(Main.class.getResourceAsStream("/image/PawnB.png"));
    static final Image BIALYP = new Image(Main.class.getResourceAsStream("/image/PawnW.png"));
    static final Image KROLOWAC = new Image(Main.class.getResourceAsStream("/image/KingB.png"));
    static final Image KROLOWAB = new Image(Main.class.getResourceAsStream("/image/KingW.png"));

    private Warcaby x = new Warcaby();                                           //utworzenie nowego obiektu klasy Warcaby

    @FXML
    public void initialize() {

        lista = FXCollections.observableArrayList(guzior2, guzior4, guzior6, guzior8, guzior9, guzior11,
                guzior13, guzior15, guzior18, guzior20, guzior22, guzior24, guzior25, guzior27, guzior29, guzior31,
                guzior34, guzior36, guzior38, guzior40, guzior41, guzior43, guzior45, guzior47, guzior50, guzior52, guzior54, guzior56,
                guzior57, guzior59, guzior61, guzior63);

        x.nowaGra();

        lista.get(0).textProperty().bindBidirectional(x.getStringProperties()[0][1]);
        lista.get(1).textProperty().bindBidirectional(x.getStringProperties()[0][3]);
        lista.get(2).textProperty().bindBidirectional(x.getStringProperties()[0][5]);
        lista.get(3).textProperty().bindBidirectional(x.getStringProperties()[0][7]);

        lista.get(4).textProperty().bindBidirectional(x.getStringProperties()[1][0]);
        lista.get(5).textProperty().bindBidirectional(x.getStringProperties()[1][2]);
        lista.get(6).textProperty().bindBidirectional(x.getStringProperties()[1][4]);
        lista.get(7).textProperty().bindBidirectional(x.getStringProperties()[1][6]);

        lista.get(8).textProperty().bindBidirectional(x.getStringProperties()[2][1]);
        lista.get(9).textProperty().bindBidirectional(x.getStringProperties()[2][3]);
        lista.get(10).textProperty().bindBidirectional(x.getStringProperties()[2][5]);
        lista.get(11).textProperty().bindBidirectional(x.getStringProperties()[2][7]);

        lista.get(12).textProperty().bindBidirectional(x.getStringProperties()[3][0]);
        lista.get(13).textProperty().bindBidirectional(x.getStringProperties()[3][2]);
        lista.get(14).textProperty().bindBidirectional(x.getStringProperties()[3][4]);
        lista.get(15).textProperty().bindBidirectional(x.getStringProperties()[3][6]);

        lista.get(16).textProperty().bindBidirectional(x.getStringProperties()[4][1]);
        lista.get(17).textProperty().bindBidirectional(x.getStringProperties()[4][3]);
        lista.get(18).textProperty().bindBidirectional(x.getStringProperties()[4][5]);
        lista.get(19).textProperty().bindBidirectional(x.getStringProperties()[4][7]);

        lista.get(20).textProperty().bindBidirectional(x.getStringProperties()[5][0]);
        lista.get(21).textProperty().bindBidirectional(x.getStringProperties()[5][2]);
        lista.get(22).textProperty().bindBidirectional(x.getStringProperties()[5][4]);
        lista.get(23).textProperty().bindBidirectional(x.getStringProperties()[5][6]);

        lista.get(24).textProperty().bindBidirectional(x.getStringProperties()[6][1]);
        lista.get(25).textProperty().bindBidirectional(x.getStringProperties()[6][3]);
        lista.get(26).textProperty().bindBidirectional(x.getStringProperties()[6][5]);
        lista.get(27).textProperty().bindBidirectional(x.getStringProperties()[6][7]);

        lista.get(28).textProperty().bindBidirectional(x.getStringProperties()[7][0]);
        lista.get(29).textProperty().bindBidirectional(x.getStringProperties()[7][2]);
        lista.get(30).textProperty().bindBidirectional(x.getStringProperties()[7][4]);
        lista.get(31).textProperty().bindBidirectional(x.getStringProperties()[7][6]);

        label.textProperty().bindBidirectional(x.getAktualnyGraczProperties());

        label.textProperty().addListener((observable, oldValue, newValue) -> {          //nalozenie listenera na label, umozliwia
            //wyswietlaniu na nim komunikatow 
            if (newValue.equals("WYGRA£ GRACZ BIALY !!!!!!") || newValue.equals("WYGRA£ GRACZ CZARNY !!!!!!")) {    //
                for (Button x : lista) {
                    x.setDisable(true);
                }

            }

        });
        for (Button x : lista) {                                                  //nalo¿enie na ka¿dy obiekt z listy listenera 
            x.textProperty().addListener(MainController::guzioryTextFieldListener);

        }
        szachownicaPoczatkowa();                           //zaladowanie szachownicy 

    }

    @FXML
    public void wybierzPionka(javafx.event.ActionEvent e) {
        Node source = (Node) e.getSource();           //rzutowanie na obiekt typu Node
        if (GridPane.getRowIndex(source) == null) {
            x.setRuch1(0); //pola zerowe wczytuje jako null, trzeba zamienic je na 0
        } else {
            x.setRuch1(GridPane.getRowIndex(source));// kolumny i wiersze
                                                     // zamienione miejscami
        }
        if (GridPane.getColumnIndex(source) == null) {
            x.setRuch2(0);
        } else {
            x.setRuch2(GridPane.getColumnIndex(source));     
        }
        
        x.graj();

    }

    private static void guzioryTextFieldListener(ObservableValue<? extends String> observable, String oldValue,
            String newValue) {

        StringProperty textProperty = (StringProperty) observable;
        Button jaki_guzior = (Button) textProperty.getBean();

        if ("1".equals(newValue)) {
            jaki_guzior.setGraphic(new ImageView(CZARNYP));
        } else if ("2".equals(newValue)) {
            jaki_guzior.setGraphic(new ImageView(BIALYP));
        } else if ("".equals(newValue)) {
            jaki_guzior.setGraphic(null);
        } else if ("3".equals(newValue)) {
            jaki_guzior.setGraphic(new ImageView(KROLOWAC));
        } else if ("6".equals(newValue)) {
            jaki_guzior.setGraphic(new ImageView(KROLOWAB));
        }
    }

    public void szachownicaPoczatkowa() {

        label.setText("Ruch gracza BIA£EGO");
        int i = 0;
        for (Button x : lista) {
            if (i < 12) {
                x.setGraphic(new ImageView(CZARNYP));
            } else if (i > 19) {
                x.setGraphic(new ImageView(BIALYP));
            } else {
                x.setGraphic(null);
            }
            i++;
        }
        if (guzior2.isDisabled() == true) {                         //sprawdza czy jeden z guzikow jest wylaczony
            for (Button x : lista) {
                x.setDisable(false);         //jesli tak, to oznacza ze wszystkie sa wylaczone 
            }                                                                //i dlatego wlacza je od poczatku 

        }
    }

    @FXML
    public void onClickRemis() {

        label.setText("Partia zakoñczona remisem. Zacznij now¹ grê");
        for (Button x : lista) {
            x.setDisable(true);
        }

    }

    @FXML
    public void onClickrestart() {
        initialize();

    }

}
