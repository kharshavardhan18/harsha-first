package greenServices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenServices.contract.CustomerListContract;
import com.greenServices.contract.LoginFormContract;

public class GreenServicesPostCliet {

	public static void main(String[] args) {


		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost postRequest = new HttpPost("http://localhost:8080/greenServices/services/login/loginForm");
			LoginFormContract loginFormContract=new LoginFormContract();

			System.out.println("in try block main method()");
			ObjectMapper mapper = new ObjectMapper();

			postRequest.addHeader("Content-Type","application/json");


			loginFormContract.setUsername("harsha");
			loginFormContract.setPassword("1234");

			String jsonInString = mapper.writeValueAsString(loginFormContract);
			System.out.println("Input Json: " + jsonInString);

			StringEntity entity = new StringEntity(jsonInString);

			postRequest.setEntity(entity);

			HttpResponse response = client.execute(postRequest);

			if (response.getStatusLine().getStatusCode() == 200) { 

				System.out.println("Rest API call is success full");
				if(response.getEntity()!=null) {
					InputStreamReader responseReader = new InputStreamReader(response.getEntity().getContent());
					BufferedReader responseBr = new BufferedReader(responseReader);

					StringBuffer result = new StringBuffer();
					String line = "";

					while ((line = responseBr.readLine()) != null) {
						//System.out.println("-" + line + "-");
						result.append(line);
					}

					System.out.println("Complete RestApi REsponse: \n " + result.toString());
				}
			}else {
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
