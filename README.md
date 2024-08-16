AUTHORS:
Muhammad Bilal 30188943
Fateh Ali Syed Bukhari 30123431

SUBWAY SCREEN PROJECT
GitHub: https://github.com/Bilal4700/SubwayScreen.git
THIS PROJECT MAKES A GUI WHICH SHOWS LIVE TRAIN MOVEMENTS , WEATHER AND 
TIME AND NEWS

To Run the code first You have to add atleast first 2 Command line arguments
Arg [0] = City in Canda (for weather And Time)
Arg [1] = Train Number 
Arg [2] = Country Code (For News, ca, us etc)(not compulsory)

To get it working you have to make a database 
Set the Hostname : 127.0.0.1
Set the Port : 3306
Username: admin
Password: subwayscreen

MAKE a SCHEMA name advertisement_db and make a TABLE named Gifs
and insert information about Gifs in Gifs folder.
-- Use the newly created database
USE advertisement_db;
-- Create the GIFs table if it does not exist
CREATE TABLE IF NOT EXISTS GIFs (
 brand_name VARCHAR(255),
 path VARCHAR(255)
);
-- Insert values into the GIFs table
INSERT INTO GIFs (brand_name, path) VALUES
('Apple', 'Gifs/Iphone.gif'),
('CocaCola', 'Gifs/CocaCola.gif'),
('EyeWear', 'Gifs/EyeWear.gif'),
('Flag', 'Gifs/CanadaFlag.gif'),
('Lays', 'Gifs/Lays.gif');
-- Verify the data
SELECT * FROM GIFs;

Information about every class and methods is fully described in Java Docs.
