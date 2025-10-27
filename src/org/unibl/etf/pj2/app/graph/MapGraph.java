package org.unibl.etf.pj2.app.graph;

import org.unibl.etf.pj2.app.state.InputData;
import org.unibl.etf.pj2.app.input.types.Departure;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.unibl.etf.pj2.app.util.DepartureUtility;
import org.unibl.etf.pj2.app.util.constants.GeneralConstants;
import org.unibl.etf.pj2.app.util.constants.StyleConstants;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Klasa izvedenica od MultiGraph-a iz GraphStream biblioteke.
 * Opisuje usmjeren graf koji moze da ima bilo koji broj ulaznih i izlaznih grana u jednom cvoru.
 * Ulazne i izlazne grane opisuju polaske sa cvora i dolaske na cvor, respektivno.
 * @author Nikola Markovic
 */
public final class MapGraph extends MultiGraph {

    /**
     * @param id Identifikator grafa
     * @author Nikola Markovic
     */
    public MapGraph(String id){
        super(id);
        this.setAttribute("ui.stylesheet", StyleConstants.GRAPH_STYLESHEET);
    }

    /**
     * Metoda za generisanje i prvobitnu distribuciju cvorova grafa po ulasku u aplikaciju.
     * @param cityNames Imena gradova iz ulazne JSON datoteke
     * @param map Mapa identifikatora cvorova i samih objekata cvorova
     * @author Nikola Markovic
     */
    public void setNodes(String[] cityNames, Map<String, Node> map){
        Arrays.stream(cityNames).forEach(cityName -> {
            Node newNode = this.addNode(cityName);
            newNode.setAttribute("ui.style", StyleConstants.NODE_STYLE_DEFAULT);
            map.put(cityName, newNode);
        });

        distributeNodes();
    }

    /**
     * Metoda koja povezuje logicki susjedne grane (gradove) koristeci pomocnu DepartureUtility klasu
     * koja radi sa podacima iz ulaznog JSON-a. U zavisnosti od odabranog kriterijuma <code>weightCriteria</code>,
     * tezine izmedju cvorova mogu da poprime sljedece vrijednosti:
     * <ul>
     * <li><code>"duration"</code> - vremenska duzina puta izmedju dva cvora</li>
     * <li><code>"price"</code> - cijena puta izmedju dva cvora</li>
     * <li><code>"vehicle"</code> - konstantna vrijednost (x cvorova proputovanih -> x tezine)</li>
     * </ul>
     * @param weightCriteria Kriterijum tezina grana
     * @author Nikola Markovic
     */
    public void connectAdjacent(String weightCriteria){
        List<Departure> departures = InputData.getInstance().getDepartureList();

        departures.forEach(departure -> {
            final String from = DepartureUtility.stationToCity(departure.getFrom());
            final String to = DepartureUtility.stationToCity(departure.getTo());
            final int weight;

            int subId = -1;
            String id;

            do {
                subId++;
                id = from + " " + to + " " + subId;
            } while (this.getEdge(id) != null);

            Edge e = this.addEdge(id, from, to, true);
            e.setAttribute("ui.style", StyleConstants.EDGE_STYLE_DEFAULT);

            switch (weightCriteria){
                case "duration" -> weight = departure.getDuration();
                case "price"    -> weight = departure.getPrice();
                case "vehicle"  -> weight = 1;
                default         -> weight = departure.getDuration();
            }
            e.setAttribute("weight", weight);
        });
    }

    /**
     * Metoda za uklanjanje svih grana u grafu.
     * @author Nikola Markovic
     */
    public void clearEdges(){
        var edges = this.edges().toList();
        edges.forEach(this::removeEdge);
    }

    /**
     * Metoda za dodavanje novih cvorova u graf sa vec ustanovljenim <code>org.unibl.etf.pj2.app.ui.style</code> atributom.
     *
     * @param id
     * Identifikator novog cvora
     *
     * @return Objekat tipa <code>Node</code> novokreiranog cvora
     */
    @Override
    public Node addNode(String id){
        Node res = super.addNode(id);
        res.setAttribute("ui.style", StyleConstants.NODE_STYLE_DEFAULT);
        return res;
    }

    /**
     * Metoda za vizuelni prikaz selektovanja i deselektovanja nekog cvora.
     *
     * @param nodeId
     * Identifikator cvora za selekciju/deselekciju
     *
     * @param state
     * Novo stanje cvora <code>nodeId</code> - selektovan (true) ili deselektovan (false). Stanje <code>false</code>
     * uvijek cvoru daje default izgled, bez obzira na vrijednost parametra <code>type</code>.
     *
     * @param type
     * Tip selekcije:
     * <ul>
     * <li><code>"A"</code> - selektovan (izgled 1)</li>
     * <li><code>"B"</code> - selektovan (izgled 2)</li>
     * <li><code>"C"</code> - selektovan (izgled 3)</li>
     * </ul>
     * Stanje koje nije jedno od ova 3 uvijek cvoru daje default izgled, bez obzira na vrijednost parametra <code>org.unibl.etf.pj2.app.state</code>.
     */
    public void setSelected(String nodeId, boolean state, String type){
        final Node n = this.getNode(nodeId);
        String style;

        if (n != null) {
            if (state) {
                switch (type) {
                    case "A" -> style = StyleConstants.NODE_STYLE_SELECTED_A;    // selected (polazak)
                    case "B" -> style = StyleConstants.NODE_STYLE_SELECTED_B;    // selected (destinacija)
                    case "C" -> style = StyleConstants.NODE_STYLE_SELECTED_C;    // selected (debug)
                    default -> style = StyleConstants.NODE_STYLE_DEFAULT;        // deselected
                }
            }
            else style = StyleConstants.NODE_STYLE_DEFAULT;

            this.getNode(nodeId).setAttribute("ui.style", style);
        }
    }

    /**
     * Metoda za podrazumijevanu distribuciju cvorova na grafickom prikazu. Cvorovi se distribuisu u grupe velicine <code>sqrt(n * m)</code>,
     * gdje svaka grupa sadrzi ~n cvorova poredanih jedan do drugog, slijeva nadesno. Svaka naredna grupa (na svakih ~m cvorova) je takodje pomjerena vertikalno
     * nagore za neku konstantnu vrijednost.
     */
    private void distributeNodes(){
        final double[] x = { 10d };
        final double[] y = { 10d };

        List<Node> nodeList = this.nodes().toList();
        for (int i = 0, j = 0; i < nodeList.size(); i++, j++){
            nodeList.get(i).setAttribute("xyz", x[0], y[0], 0);

            if (j == (int)Math.sqrt(nodeList.size())) {
                y[0] += GeneralConstants.NODE_DISTANCE_Y;
                j = 0;
            }

            x[0] += GeneralConstants.NODE_DISTANCE_X;
        }
    }

    /**
     * Prethodno koristena metoda za distribuciju cvorova. Cvorovi se distribuisu nasumicno tako da se postigne sto je veca moguca vidljivost
     * pojedinacnih cvorova, odnosno sto je manje moguce preklopljenih cvorova.
     */
    private void distributeNodesLegacy(){
        final Random random = new Random();
        double minDistX = 5000d;
        double minDistY = 5000d;
        for (Node node : this){
            double x = random.nextDouble() * 5000 + minDistX;
            minDistX += random.nextDouble() * 75;

            double y = random.nextDouble() * 5000 + minDistY;
            minDistY += random.nextDouble() * 100 * (random.nextBoolean() ? 1 : -1);
            node.setAttribute("xyz", x, y, 0);
        }
    }
}
