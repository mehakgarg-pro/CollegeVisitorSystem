-- Drop the table if it already exists (optional, for clean setup)
DROP TABLE IF EXISTS Visitors_temp;

-- Create a new table with AUTO_INCREMENT for ID
CREATE TABLE Visitors_temp (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    phone VARCHAR(20),
    purpose VARCHAR(100),
    person_to_meet VARCHAR(100),
    visit_date DATE,
    visit_time TIME
);

-- Insert existing visitor data into the new table
INSERT INTO Visitors_temp (name, phone, purpose, person_to_meet, visit_date, visit_time)
SELECT name, phone, purpose, person_to_meet, visit_date, visit_time
FROM Visitors
ORDER BY visit_date, visit_time;

-- Verify the table creation and IDs
SELECT * FROM Visitors_temp;
