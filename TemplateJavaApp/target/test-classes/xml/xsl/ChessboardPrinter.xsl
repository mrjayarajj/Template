<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">

	<xsl:strip-space elements="*" />

	<xsl:output method="text" />

	<xsl:template match="/">
		<xsl:apply-templates />
	</xsl:template>

	<xsl:template match="WHITEPIECES/*">
		<xsl:value-of select="concat('White ', name(),
    ': ', POSITION/@COLUMN, POSITION/@ROW)" />
		<xsl:text></xsl:text>
	</xsl:template>

	<xsl:template match="BLACKPIECES/*">
		<xsl:value-of select="concat('Black ', name(),
    ': ', POSITION/@COLUMN, POSITION/@ROW)" />
		<xsl:text></xsl:text>
	</xsl:template>

</xsl:stylesheet>
