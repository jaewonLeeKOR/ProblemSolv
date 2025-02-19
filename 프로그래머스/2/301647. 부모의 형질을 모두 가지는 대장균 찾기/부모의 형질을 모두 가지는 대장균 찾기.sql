SELECT c.id ID, c. genotype GENOTYPE, p.genotype PARENT_GENOTYPE
FROM ECOLI_DATA c
INNER JOIN ECOLI_DATA p
ON c.parent_id = p.id
WHERE p.genotype & c.genotype = p.genotype
ORDER BY c.id ASC