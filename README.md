User/HR
  |
  v
Frontend (UI)
  |  Upload resumes & JD
  v
AnalysisController
  |  
  v
ResumeParser → SkillExtractor → SkillGap → Scoring → Eligibility → Recommendation
  |
  v
H2 Database (Save results)
  |
  v
Frontend Display (Score, Matched/Missing Skills, Projects, Eligibility, Suggestions)

