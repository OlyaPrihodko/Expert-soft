<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mytag" uri="customtags" %>
<fmt:requestEncoding value="utf-8"/>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="/com.expert_soft.prihodko.task/localization.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.message.Contacts" var="Contacts"/>
<fmt:message bundle="${loc}" key="locale.message.Task" var="Task"/>
<fmt:message bundle="${loc}" key="locale.message.Back" var="Back"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${language}">
<head>
    <title>${Contacts}</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html" charset="utf-8">
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/bootstrap-theme.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/bootstrap-datetimepicker.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/font-awesome.min.css"/>" rel="stylesheet">

    <link href="<c:url value="/css/style.css"/>" rel="stylesheet">

</head>
<body>
<c:set scope="session" value="/WEB-INF/jsp/main.jsp" var="previous-page"/>
<c:set scope="session" value="/WEB-INF/jsp/contacts.jsp" var="current-page"/>
<jsp:useBean id="Contact" beanName="com.expert_soft.prihodko.task.entity.Contact"
             type="com.expert_soft.prihodko.task.entity.Contact" scope="session"/>
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
                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-4 col-lg-offset-8" >
                                <div class="btn-group">
                                    <input type="hidden" id="language" name="language" value="" form="form"/>
                                    <button type="submit" onclick="document.getElementById('language').value='en_EN';
                                    document.getElementById('command').value='change-language';" form="form">
                                        <img src="images/flag_great_britain.png" width="40" height="30">
                                    </button>
                                    <button type="submit" onclick="document.getElementById('language').value='ru_RU';
                                    document.getElementById('command').value='change-language';" form="form">
                                        <img src="images/flag_russia.png" width="40" height="30">
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <form role="form" action="controller" method="post" id="form">
                    <input type="hidden" id="command" name="command" value=""/>
                    <div class="panel-body">
                        <div class="row" align="center">
                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-10 col-lg-offset-1">
                                <mytag:maptag collection="${contacts}" lang="${language}"/>
                            </div>
                        </div>
                        <div class="row" align="center">
                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 col-lg-offset-2">
                                <div id="pager" class="pager">
                                    <form>
                                        <img src="images/first.png" class="first"/>
                                        <img src="images/prev.png" class="prev"/>
                                        <input type="text" class="pagedisplay"/>
                                        <img src="images/next.png" class="next"/>
                                        <img src="images/last.png" class="last"/>

                                        <select class="pagesize">
                                            <option selected="selected"  value="10">10</option>
                                            <option  value="20">20</option>
                                            <option  value="30">30</option>
                                        </select>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="row" align="center">
                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 col-lg-offset-3">
                                <div class="btn-group-vertical">
                                    <button  type="submit" class="btn btn-labeled btn-primary"
                                             onclick="document.getElementById('command').value='previous-page';">
                                                  <span class="btn-label" ><i class="fa fa-arrow-left"></i>
                                                  </span> ${Back}
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
    </form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/bootstrap-datetimepicker.min.js"></script>
<script src="/js/moment-with-locales.min.js"></script>

<script src="/js/jquery-latest.js" type="text/javascript"></script>
<script src="/js/jquery.tablesorter.js" type="text/javascript"></script>
<script src="/js/jquery.tablesorter.pager.js" type="text/javascript"></script>
<script src="/js/script.js" type="text/javascript"></script>
</body>
</html>