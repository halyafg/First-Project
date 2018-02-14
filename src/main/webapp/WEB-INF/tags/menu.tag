
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag body-content="empty" %>


<div class="navbar">
    <ul>
        <li><a class="bot activ" href="/">Home page</a></li>
        <li><sec:authorize access="isAnonymous()"><a class="bot" href="/loginpage">Sign in</a></sec:authorize></li>
        <li><sec:authorize access="hasRole('ROLE_USER')"><a class="bot" href="/cabinet">My cabinet </a></sec:authorize></li>
        <li><sec:authorize access="hasRole('ROLE_USER')"><a class="bot" href="/change/passwordpage">Change a password</a></sec:authorize>
        <li><sec:authorize access="hasRole('ROLE_ADMIN')"><a class="bot" href="/customers/all/inhouse/${house.id}">Customers </a> </sec:authorize></li>
        <li><sec:authorize access="hasRole('ROLE_ADMIN')"><a class="bot" href="/flats/all/${house.id}">Flats </a> </sec:authorize></li>
        <li><sec:authorize access="hasRole('ROLE_ADMIN')"><a class="bot" href="/pantries/all/${house.id}">Pantries </a> </sec:authorize></li>
        <li><sec:authorize access="hasRole('ROLE_ADMIN')"><a class="bot" href="/parkings/all/${house.id}">Parking places </a> </sec:authorize></li>
        <li><sec:authorize access="hasRole('ROLE_ADMIN')"><a class="bot" href="/schedules/all/${house.id}">Payment Schedules </a> </sec:authorize></li>
        <li><sec:authorize access="hasRole('ROLE_ADMIN')"><a class="bot" href="/payments/all/${house.id}">Actual payments </a> </sec:authorize></li>
        <li><sec:authorize access="isAuthenticated()"><a class="bot logout" href="/logout">Sign up</a></sec:authorize></li>

    </ul>
</div>


