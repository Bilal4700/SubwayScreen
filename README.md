# 🚇 Calgary Subway Simulation System

This project is a desktop simulation of a subway system in **Calgary**, built entirely in **Java**. It provides a live, interactive view of trains moving across a subway map and allows you to explore additional real-time information like weather forecasts and news updates — all in one place.

👉 **[📺 Watch the Video Demo](https://youtu.be/M22Fni-hTZ0)**

---

## 🗺️ What the Project Does

The simulation displays trains moving across a subway network map in real time, giving you a visual experience of how a subway system operates. One of the main features is the ability to **highlight a specific train** by its number — that train will appear in **yellow** on the map so you can easily follow it while all other trains continue moving normally.

Alongside the train simulation, the system connects to external APIs to display **live weather forecasts** and **news updates** right inside the application:

- 🌤️ **Weather Forecast:** Enter the name of any city in Canada, and the simulation will show you the current weather forecast for that city.
- 📰 **News Feed:** Provide a country code (like `ca` for Canada or `us` for the United States), and the system will display the latest news headlines from that country.

These three pieces of information — city, train number, and country code — are provided as arguments when running the simulation. 
The order is: [City] [train number] [country code]


