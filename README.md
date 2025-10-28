<h2>Introduction</h2>
The app offers a pathfinding solution with visual representation. The main graph represents a country, where each node is a city and directed edges between them represent departures or arrivals to that city with a particular vehicle at a particular time. This data is loaded from <code>transport_dataMP.json</code> or <code>transport_data.json</code> upon start.

<h2>Visual presentation</h2>
The app features a basic Swing-based UI with 3 distinct parts - the visual representation of the aforementioned graph, the "Rezultat" (result) bar and the "Opcije" (options) bar.
<img width="1277" height="860" alt="image" src="https://github.com/user-attachments/assets/6694a39c-94d8-44f7-93dc-6043ad6f064b" />

<h2>How to use</h2>
The options bar allows the user to select two nodes between which the pathfinder will attempt to find the most optimal path using criteria set with the "Optimizuj po" (optimize by) drop-dowm list of criteria. There are currently three possible criteria - "Najniza cijena" (lowest price), "Najkrace vrijeme puta" (shortest time to arrive) and "Najmanji broj presjedanja" (lowest number of vehicle changes). Using the data loaded earlier from the initial JSON file, the pathfinder will, upon pressing the "Pronadji" (find) button, visually display the pathfinding process, finishing when the most optimal path has been highlighted in its entirety.
<img width="1277" height="860" alt="image" src="jtgif.gif" />

<h2>Extras</h2>
After finding the optimal path, by pressing the "Detaljno" (detailed info) button, another window will open displaying the 5 most optimal paths (the most optimal one and 4 closest).
<img width="1277" height="860" alt="image" src="https://github.com/user-attachments/assets/d7d43211-bf29-464c-ac00-ffe48799f2fb" />

By selecting one, you'll be prompted to choose to buy a ticket with the "Kupi kartu" (buy ticket) button. Upon pressing said button, another window will open detailing the route even further, as well as the price and departure and arrival times.
<img width="1276" height="857" alt="image" src="https://github.com/user-attachments/assets/db083691-7079-454c-86ed-9a792334fe9f" />

By pressing the "Potvrdi kupovinu" (confirm purchase) button, a receipt will be printed and saved to your PC in a new directory created in the exact same directory the app jar was run from.
<img width="844" height="228" alt="image" src="https://github.com/user-attachments/assets/1d562981-6ff4-49fa-9a7c-3c7fc266e6d7" />
