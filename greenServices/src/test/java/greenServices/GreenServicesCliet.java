package greenServices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GreenServicesCliet {

	public static void main(String[] args) {

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet("http://localhost:8080/greenServices/services/secAcct/userInfo/23");
		try {

			HttpResponse response = client.execute(request);

			if (response.getStatusLine().getStatusCode() == 200) {
				System.out.println("Rest API call is success full");

				InputStreamReader responseReader = new InputStreamReader(response.getEntity().getContent());
				BufferedReader responseBr = new BufferedReader(responseReader);

				
				StringBuffer result = new StringBuffer();
				String line = "";
				
				while ((line = responseBr.readLine()) != null) {
					//System.out.println("-" + line + "-");
					result.append(line);
				}
				
				System.out.println("Complete RestApi REsponse: \n " + result.toString());
				
				ObjectMapper mapper = new ObjectMapper();
				
				UserBean2 user = mapper.readValue(result.toString(), UserBean2.class);
				
				System.out.println("Values after converting Json to Java *** ");
				System.out.println("UserId = " + user.getUserId());
				System.out.println("FirstName = " + user.getFirstName());
				System.out.println("LastName = " + user.getLastName());
				
			} else {
				System.out.println("Status Code : " + response.getStatusLine().getStatusCode());
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
