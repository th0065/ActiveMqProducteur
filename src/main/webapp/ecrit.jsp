<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestion de message de diffusion</title>


 <style><%@include file="/css/student.css"%>
 <%@include file="/css/bootstrap.css"%>
  <%@include file="/css/fas.css"%>
 
 </style>
</head>
<body>


<section class="intro">
  <div class="bg-image h-100" style="background-color: #6095F0;">
    <div class="mask d-flex align-items-center h-100">
      <div class="container">
        <div class="row justify-content-center">
          <div class="col-12">
            <div class="card shadow-2-strong" style="background-color: #f5f7fa;">
              <div class="card-body">
             <% if(request.getAttribute("error") != null){%>
                <div class="row form-group">
                     <hr>
                    <div class="col-md-6 ">
                        <span class='text-danger '><%= request.getAttribute("error") %></span>
                    </div>
                     <hr>
                </div>
                    <%}%> 
                <div class="table-responsive">
                
                  <form method="post" action="controller" class="m-5">
                 
                 
                   
                
                   
                  <table class="table table-borderless mb-0">
                    <thead>
                      <tr>
                        
                        <th scope="col"> 
                        <div class="form-group">
                          <label>Objet :</label>
                            <input class="form-input" type="text"  id="objet" name="objet" />
                          </div>
                          </th>
                         
                        <th scope="col">
                         <div class="form-group">
                          <label>Message  :</label>
                            <input class="form-input" type="text"  id="message" name="message" />
                          </div>
                          </th>
                           </tr>
                         
                      
                     
                        
                        
                    </thead>
                    <tbody>
                     
                      
            
                      
                    </tbody>
                  </table>
                  <div class="m-5 "> <button type="submit" class="btn btn-sm btn-success px-5">Enregistrer</button></div>
                 
                   
                    
                   
                   
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
</body>
</html>