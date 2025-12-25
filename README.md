      +--------------------+
      |   User / HR Panel  |
      |  (Upload Resume &  |
      |   Job Description) |
      +---------+----------+
                |
                v
      +--------------------+
      |   Frontend (HTML)  |
      | - Form to upload   |
      |   resumes          |
      | - Display results  |
      +---------+----------+
                |
                v
      +------------------------------+
      |     AnalysisController       |
      | - Receives /analyze request  |
      | - Calls services sequentially|
      +---------+--------------------+
                |
     +----------+----------+----------------+
     |                     |                |
     v                     v                v
+-----------+        +------------+    +-----------------+
| Resume    |        | Skill Gap  |    | Scoring Service |
| Parser    |        | Service    |    | - Weighted      |
| - PDF →   |        | - Compares |    |   Score         |
|   Text    |        |   JD &     |    |                 |
| - Clean   |        |   Resume   |    +-----------------+
| Text      |        +------------+
+-----------+              
       |
       v
+----------------------+
| SkillExtractorService|
| - Extracts skills    |
| - Experience         |
| - Education          |
| - Projects/Tools     |
| - Skill Strength     |
+----------------------+
       |
       v
+------------------------+
| EligibilityService     |
| - Checks score         |
| - Checks experience    |
| - Checks missing skills|
+------------------------+
       |
       v
+------------------------+
| RecommendationService  |
| - Suggests improvements|
| - Template based       |
+------------------------+
       |
       v
+---------------------------+
|       H2 Database         |
| - Saves AnalysisResult    |
| - Fields: candidate,      |
|   matched/missing skills, |
|   experience, score,      |
|   recommendations         |
+---------------------------+
       |
       v
+------------------------------------------+
|        Frontend / UI Display             |
| - Shows match score                      |
| - Shows matched/missing skills           |
| - Shows projects & education             |
| - Shows eligibility & recommendations    |
+------------------------------------------+
