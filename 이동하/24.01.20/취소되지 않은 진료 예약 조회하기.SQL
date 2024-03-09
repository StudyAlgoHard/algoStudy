-- 코드를 입력하세요
# SELECT n.APNT_NO, n.PT_NAME, n.PT_NO, m.MCDP_CD, m.DR_NAME, n.APNT_YMD
# from (select a.APNT_NO, p.PT_NAME, p.PT_NO, CONCAT(a.APNT_YMD, '.000000') as APNT_YMD, a.APNT_CNCL_YN from APPOINTMENT a join PATIENT p on a.PT_NO = p.PT_NO) n,
# (select a.APNT_NO, d.MCDP_CD, d.DR_NAME from APPOINTMENT a join DOCTOR d on a.MDDR_ID = d.DR_ID) m
# where n.APNT_NO = m.APNT_NO
# and n.APNT_CNCL_YN = 'N'
# and DATE_FORMAT(n.APNT_YMD, "%y") = 22
# and DATE_FORMAT(n.APNT_YMD, "%m") = 4
# and DATE_FORMAT(n.APNT_YMD, "%d") = 13
# and m.MCDP_CD = 'CS'
# order by n.APNT_YMD 

select * from APPOINTMENT a
join PATIENT p on a.PT_NO = p.PT_NO
where a.MCDP_CD = 'CS' and a.APNT_CNCL_YN = 'N' and DATE_FORMAT(a.APNT_YMD, "%m") = "04" and DATE_FORMAT(a.APNT_YMD, "%d") = 13