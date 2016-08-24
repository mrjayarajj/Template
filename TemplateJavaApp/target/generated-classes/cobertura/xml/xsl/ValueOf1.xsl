<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
	version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="text" />
	<xsl:template match="firstname">
		We found a first name and it's
		<xsl:value-of select="." />
	</xsl:template>
</xsl:stylesheet>
