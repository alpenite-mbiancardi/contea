<%@page import="com.alpenite.tea.communicationLayer.data.Constants"%>
<%@page import="org.jahia.services.content.JCRNodeWrapper"%>
<%@page import="com.alpenite.tea.communicationLayer.WSClient"%>
<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr"%>
<%@ taglib prefix="functions" uri="http://www.jahia.org/tags/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="utility" uri="http://www.jahia.org/tags/utilityLib"%>
<%@ taglib prefix="functions" uri="http://www.jahia.org/tags/functions"%>


<script type="text/javascript">


	function testFunction(input, row) {
		console.log(input.files[0].name);
		console.log(input.files[0].size);

		fullName = input.files[0].name;
		shortName = fullName.match(/[^\/\\]+$/);
		dim = bytesToSize(input.files[0].size);
		$('#fileNameRow' + row).prepend(shortName);
		$('#fileDimRow' + row).prepend(dim);
		$('#removeFile' + row).css("visibility", "visible");

		console.log(dim);
	};

	function clearFunction(row) {

		$('#fileNameRow' + row).html("");
		$('#fileDimRow' + row).html("");
		$('#uploadfile' + row).val('');
		$('#removeFile' + row).css("visibility", "hidden");
		$('#massageDivDelete').dialog('close');
	};

	function bytesToSize(bytes) {
		var sizes = [ 'n/a', 'bytes', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB',
				'YB' ];
		var i = +Math.floor(Math.log(bytes) / Math.log(1024));
		return (bytes / Math.pow(1024, i)).toFixed(i ? 1 : 0) + ' '
				+ sizes[isNaN(bytes) ? 0 : i + 1];
	};

	function showDivMessage(idDiv) {

		var $dialog1 = $('#' + idDiv).dialog({
			autoOpen : false,
			modal : true,
			draggable : false,
			width : 220,
			resizable : false,
			closeText : "X"
		});
		$('#' + idDiv).css("visibility", "visible");
		$dialog1.dialog('open');

	};

	function closiDialog(idDiv) {
		$('#' + idDiv).dialog('close');

	};

	function showDeleteDivMessage(idDiv,row) {

		var $dialog1 = $('#' + idDiv).dialog({
			autoOpen : false,
			modal : true,
			draggable : false,datiAnagrafici
			width : 220,
			resizable : false,
			closeText : "X"
		});
		$('#' + idDiv).css("visibility", "visible");
		$('#yesDeletbutton').attr('onclick','clearFunction('+row+')');
		$dialog1.dialog('open');

	};
	
	function saveButttonActive(){
		var active=0;
		if($(uploadfile1).val()!=""){
			active=active+1;
		}
		if($(uploadfile2).val()!=""){
			active=active+1;
		}
		if($(uploadfile3).val()!=""){
			active=active+1;
		}
		if(active!=0){
			$('#saveButton').submit();
		}
	}
</script>




<div class="open"></div>
<div class="title">
	<h1><fmt:message key="uploadMandatoRevoca.title"></fmt:message> </h1>
</div>
<div class="txt">
	<p>${currentNode.properties.textPage.string}</p>
</div>

<div style="clear: both; width: 100%; height: 2px;"></div>

<form id="uploadFiles" enctype="multipart/form-data"
	action="${url.base}${currentNode.path}.sendFileToSap.do?cc=${param['cc']}&isRevoke=${param['isRevoke']}" method="post">
	<input type="hidden" name="jcrRedirectTo"
		value="${url.base}${renderContext.mainResource.node.path}" />
	<div class="box pag">
		<div class="open"></div>



		<table id="tabellaContratti"
			class="tbl tabella tablesorter addDocumentTable">
			<thead id="tabellaContrattiHeader" class="header">
				<tr>
					<th class="colonna header first"><fmt:message
							key="uploadMandatoRevoca.fileName" /></th>
					<th class="colonna header"><fmt:message
							key="uploadMandatoRevoca.fileDim" /></th>
					<th class="colonna header"></th>
					<th class="colonna header last"></th>
				<tr>
			</thead>
			<tbody id="tabellaContrattiRighe">
				<tr class="riga">
					<td class="bg colonna first focus" id="fileNameRow1"></td>
					<td class="bg colonna focus" id="fileDimRow1"></td>
					<td class="bg colonna focus"><label>Carica
							<span> <input class=addDocumentTable type="file"
								id="uploadfile1" style="display: none;"name="fileField0"
								onchange="testFunction(this,1);">
						</span>
					</label></td>
					<td class="bg colonna last focus"><a id="removeFile1"
						style="visibility: hidden;" onclick="showDeleteDivMessage('massageDivDelete',1);">Rimuovi</a></td>
				</tr>
				<tr class="riga">
					<td class="bg colonna first focus" id="fileNameRow2"></td>
					<td class="bg colonna focus" id="fileDimRow2"></td>
					<td class="bg colonna focus" ><label>Carica
							<span> <input type="file" id="uploadfile2"
								style="display: none;" name="fileField1"
								onchange="testFunction(this,2);">
						</span>
					</label></td>
					<td class="bg colonna last focus"><a id="removeFile2"
						style="visibility: hidden;" onclick="showDeleteDivMessage('massageDivDelete',2);">Rimuovi</a></td>
				</tr>
				<tr>
					<td class="bg colonna first focus" id="fileNameRow3"></td>
					<td class="bg colonna focus" id="fileDimRow3"></td>
					<td class="bg colonna focus" ><label>Carica
							<span> <input type="file" id="uploadfile3"
								name="fileField2" style="display :none;" onchange="testFunction(this,3);">
						</span>
					</label></td>

					<td class="bg colonna last focus"><a id="removeFile3"
						style="visibility: hidden;" onclick="showDeleteDivMessage('massageDivDelete',3)">Rimuovi</a></td>
				</tr>
			</tbody>
		</table>




		<div style="width: both;"></div>



		<div class="pagination">
			<div class="paginationNavigation">
				<button type="button" class="btn medium left"
					onClick="showDivMessage('massageDivBack')">
					<fmt:message key="uploadMandatoRevoca.backReturn" />
				</button>
				<button id="saveButton" class="btn medium"  onclick="saveButttonActive()">
					<fmt:message key="uploadMandatoRevoca.saveDocument" />
				</button>
			</div>
		</div>



	</div>
</form>

<div id="massageDivBack" style="visibility: hidden;" class="messaggio">
	<div class="txt">
		<p>
			<fmt:message key="uploadMandatoRevoca.backReturnMessage" />
		</p>
	</div>
	<div style="clear: both; height: 40px;"></div>
	<div style="margin-left: 10%; margin-right: 10%; ">
			<span class="customerOpt" style="float: left;">
				<a href="${url.base}${currentNode.properties.backReturn.node.path}.html" style="width: 50px;" class="btn">
					<fmt:message key="uploadMandatoRevoca.backReturnYes" />
				</a>
			</span>
			
			<span class="customerOpt"  style="float:right;width: 50px; ">
				<a onclick="closiDialog('massageDivBack')" style="width: 50px;"class="btn">
					<fmt:message key="uploadMandatoRevoca.backReturnNo" />
				</a>
			</span>
</div>
</div>

<div id="massageDivDelete" style=" visibility:hidden; width: 60%;" class="messaggio" >
<div class="txt">
	<p><fmt:message key="uploadMandatoRevoca.deleteMessage" />
	</p>
</div>
<div style="clear: both; height:40px;"></div>
<div  style="margin-left: 10%; margin-right: 10%; ">
<span class="customerOpt" style="float: left;">
				<a id= "yesDeletbutton" style="width: 50px;" class="btn">
					<fmt:message key="uploadMandatoRevoca.backReturnYes" />
				</a>
			</span>
			
			<span class="customerOpt"  style="float:right;width: 50px; ">
				<a onclick="closiDialog('massageDivDelete')" style="width: 50px;" class="btn">
					<fmt:message key="uploadMandatoRevoca.backReturnNo" />
				</a>
			</span>
</div>
</div>

