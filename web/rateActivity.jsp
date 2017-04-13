
	
<div class="card text-center">
  <div class="card-block">
    <h4 class="card-title">Rate Activity</h4>
   
    
    <p class="card-text">
 <p>click the stars and submit</p>
 															
  <div class="stars">
     <form action="RateActivity"  method="post">
     <input type="hidden" name="activityID" value="<c:out value="${activity_id}"></c:out>"> 
      <input value=5 class="star star-5" id="star-5" type="radio" name="star"/>
      <label class="star star-5" for="star-5"></label>
      <input value=4 class="star star-4" id="star-4" type="radio" name="star"/>
      <label class="star star-4" for="star-4"></label>
      <input value=3 class="star star-3" id="star-3" type="radio" name="star"/>
      <label class="star star-3" for="star-3"></label>
      <input value=2 class="star star-2" id="star-2" type="radio" name="star"/>
      <label class="star star-2" for="star-2"></label>
      <input value=1 class="star star-1" id="star-1" type="radio" name="star"/>
      <label class="star star-1" for="star-1"></label>
      <button type="submit" class="btn btn-primary">Submit</button>
    </form>
  </div>

										
										
										
										
										
										
										
															
										
		
</div>
						
									
										
  </div>
</div>

</body>
</html>