📌Project Overview
Resume Matcher is a Java Spring Boot web application that helps recruiters analyze and rank resumes based on a given job description.
The system automatically:
Extracts skills, education, experience, and projects from resumes
Matches them with job requirements
Calculates a match score
Checks candidate eligibility
Ranks candidates based on score

📌Technologies Used
Java
Spring Boot
Spring Data JPA
H2 Database
HTML, CSS, JavaScript
Apache PDFBox (for reading PDF resumes)

📌How the System Works

Step 1: Upload Input
HR uploads multiple PDF resumes
Enters required skills

Step 2: Resume Parsing
The system reads resume PDFs using PDFBox
Converts resume content into plain text

Step 3: Skill & Data Extraction
From resumes, the system extracts:
Technical skills (Java, SQL, HTML, etc.)
Years of experience
Education details
Project titles
From job description:Required skills are extracted

Step 4: Skill Gap Analysis
Matched Skills → Skills present in resume 
Missing Skills → Skills required but not found in resume

Step 5: Score Calculation
Final score is calculated using:
Skills match – 50%
Experience – 30%
Education – 20%

Step 6: Eligibility Check
Candidate is marked NOT ELIGIBLE if:
Match score is below 60%
Experience is less than 1 year
Required skills are missing

Step 7: Recommendations
If skills are missing, the system suggests:
Adding projects or experience related to missing skills

Step 8: Store Results
All analysis results are saved in H2 Database
Used for ranking and history tracking

Step 9: Ranking Candidates
Candidates are sorted based on match score
Highest score appears first

📌Project Architecture
Controller → Service → Repository → Database

Main Layers:

Controller – Handles API requests
Service – Business logic (scoring, eligibility, parsing)
Repository – Database interaction
Entity – Stores resume analysis data
Frontend – HTML + JavaScript UI
