-- Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS advertisement_db;

-- Select the database
USE advertisement_db;

-- Drop the table if it exists
DROP TABLE IF EXISTS pictures;

-- Create the table
CREATE TABLE pictures (
    brand_name VARCHAR(255) PRIMARY KEY,
    path VARCHAR(255) NOT NULL
);

-- Insert default data
INSERT INTO pictures (brand_name, path) VALUES
('Tesla', 'Gifs/Tesla.gif'),
('EyeWear', 'Gifs/EyeWear.gif'),
('Flag', 'Gifs/CanadaFlag.gif'),
('Lays', 'Gifs/Lays.gif');

