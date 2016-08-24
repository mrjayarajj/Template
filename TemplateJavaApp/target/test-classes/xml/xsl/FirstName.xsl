<?xml version="1.0"?>
<xsl:stylesheet
	version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="text" />

	<xsl:template match="/">
		<html>
			<body>
				<h2>My CD Collection</h2>
				<xsl:apply-templates />
			</body>
		</html>
	</xsl:template>

	<xsl:template match="firstname">We found a first name!</xsl:template>
</xsl:stylesheet>
