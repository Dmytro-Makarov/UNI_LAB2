<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                version="1.0">

    <xsl:template match="/">
        <html>

            <head>
                <style type="text/css">
                    table.tfmt {
                    border: 1px ;
                    }

                    td.colfmt {
                    border: 1px ;
                    background-color: white;
                    color: black;
                    text-align:right;
                    }

                    th {
                    background-color: #2E9AFE;
                    color: white;
                    }

                </style>
            </head>

            <body>
                <table class="tfmt">
                    <tr>
                        <th style="width:250px">Name:</th>
                        <th style="width:350px">Annotation:</th>
                        <th style="width:250px">Type:</th>
                        <th style="width:250px">Author:</th>
                        <th style="width:250px">Terms of Use:</th>
                        <th style="width:250px">Address:</th>
                    </tr>

                    <xsl:for-each select="DepartmentDataBase/resource">

                        <tr>
                            <td class="colfmt">
                                <xsl:value-of select="@NAME" />
                            </td>
                            <td class="colfmt">
                                <xsl:value-of select="@ANNOTATION" />
                            </td>
                            <td class="colfmt">
                                <xsl:value-of select="@TYPE" />
                            </td>
                            <td class="colfmt">
                                <xsl:value-of select="@AUTHOR" />
                            </td>
                            <td class="colfmt">
                                <xsl:value-of select="@TERMS_OF_USE" />
                            </td>
                            <td class="colfmt">
                                <xsl:value-of select="@ADDRESS" />
                            </td>
                        </tr>

                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>