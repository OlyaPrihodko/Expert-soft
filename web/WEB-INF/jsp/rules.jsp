<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:requestEncoding value="utf-8"/>
<fmt:setBundle basename="/com.expert_soft.prihodko.task/localization.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.message.Back" var="Back"/>
<fmt:message bundle="${loc}" key="locale.message.Task" var="Task"/>
<fmt:message bundle="${loc}" key="locale.message.Rules" var="Rules"/>

<fmt:message bundle="${loc}" key="locale.message.DescriptionRules" var="DescriptionRules"/>
<fmt:message bundle="${loc}" key="locale.message.Description" var="Description"/>
<fmt:message bundle="${loc}" key="locale.message.Example" var="Example"/>
<fmt:message bundle="${loc}" key="locale.message.ExampleRules" var="ExampleRules"/>
<html lang="${language}">
<head>
    <title>${Rules}</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html" charset="utf-8">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
</head>
<body>


<c:set scope="session" value="/WEB-INF/jsp/main.jsp" var="previous-page"/>
<c:set scope="session" value="/WEB-INF/jsp/rules.jsp" var="current-page"/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<div class="container-fluid" >
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-lg-offset-3 col-lg-6">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h2 class="panel-title text-center">
                        ${Rules}
                    </h2>
                </div>
                <div class="panel-body">
                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 col-lg-offset-2" >
                        <form role="form" action="controller" method="post">
                            <input type="hidden" id="command" name="command" value=""/>
                            <div id="accordion" class="panel-group">
                                <div class="panel panel-info">
                                    <div class="panel-heading">
                                        <h4 class="panel-title"><a href="#collapse-1" data-parent ="#accordion" data-toggle="collapse">${DescriptionRules}</a></h4>
                                    </div>
                                    <div id="collapse-1" class="panel-collapse collapse in">
                                        <div class="panel-body">
                                            ${Description}
                                        </div>
                                    </div>
                                </div>
                                <div class="panel panel-info">
                                    <div class="panel-heading">
                                        <h4 class="panel-title"><a href="#collapse-2" data-parent ="#accordion" data-toggle="collapse">${ExampleRules}</a></h4>
                                    </div>
                                    <div id="collapse-2" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            ${Example}
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-5 col-lg-offset-5">
                                <div class="btn-group">
                                    <button  type="submit" class="btn btn-labeled btn-primary"
                                             onclick="document.getElementById('command').value='previous-page';">
                                              <span class="btn-label" ><i class="fa fa-arrow-left"></i>
                                              </span> ${Back}
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <%@include file="/WEB-INF/jsp/footerPart.jsp"%>
            </div>
        </div>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>