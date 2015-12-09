<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="utf-8"/>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="/com.expert_soft.prihodko.task/localization.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.message.Welcome" var="Welcome"/>
<fmt:message bundle="${loc}" key="locale.message.MainPage" var="MainPage"/>
<fmt:message bundle="${loc}" key="locale.message.Task" var="Task"/>
<fmt:message bundle="${loc}" key="locale.message.Import" var="Import"/>
<fmt:message bundle="${loc}" key="locale.message.View" var="View"/>
<fmt:message bundle="${loc}" key="locale.message.Message1" var="Message1"/>
<fmt:message bundle="${loc}" key="locale.message.Rules" var="Rules"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${language}">
<head>
    <title>${MainPage}</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html" charset="utf-8">
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/bootstrap-theme.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/bootstrap-datetimepicker.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/font-awesome.min.css"/>" rel="stylesheet">

</head>
<body>
<c:set scope="session" value="/WEB-INF/jsp/main.jsp" var="current-page"/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<div class="container">
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-6 col-md-offset-3 col-lg-offset-3 col-lg-6">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <div class="panel-title">
                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-5 col-lg-offset-1" >
                                <h4>${Welcome} <i class="fa fa-smile-o"></i></h4>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-4 col-lg-offset-2" >
                                <form>
                                    <input type="hidden" id="language" name="language" value=""/>
                                    <div class="btn-group">
                                        <button type="submit" onclick="document.getElementById('language').value='en_EN';
                                        document.getElementById('command').value='change-language';">
                                            <img src="images/flag_great_britain.png" width="40" height="30">
                                        </button>
                                        <button type="submit" onclick="document.getElementById('language').value='ru_RU';
                                        document.getElementById('command').value='change-language';">
                                            <img src="images/flag_russia.png" width="40" height="30">
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <form role="form" id="form" action="controller" method="post">
                    <input type="hidden" id="command" name="command" value=""/>
                    <div class="panel-body">
                        <div class="row text-center">
                            <h4>${Message1}</h4>
                        </div>
                        <br/>
                        <div class="row" align="center">
                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 col-lg-offset-3">
                                <div class="btn-group-vertical">
                                    <button type="submit" class="btn btn-labeled btn-primary"
                                            onclick="document.getElementById('command').value='go-to-import-page';">
                                        <span class="btn-label" type="submit"><i class="glyphicon glyphicon-import" style="font-size: 120%"></i></span> ${Import}
                                    </button>
                                    <button type="submit" class="btn btn-labeled btn-primary"
                                            onclick="document.getElementById('command').value='go-to-contacts-page';">
                                        <span class="btn-label" type="submit"><i class="fa fa-list-alt" style="font-size: 120%"></i></span> ${View}
                                    </button>
                                    <button type="submit" class="btn btn-labeled btn-info"
                                            onclick="document.getElementById('command').value='go-to-rules-page';">
                                        <span class="btn-label" type="submit"><i class="fa fa-info" style="font-size: 120%"></i></span> ${Rules}
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%@include file="/WEB-INF/jsp/footerPart.jsp"%>
                </form>
            </div>
        </div>
    </div>

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/bootstrap-datetimepicker.min.js"></script>
<script src="/js/moment-with-locales.min.js"></script>
</body>
</html>