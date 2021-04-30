package app;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

    public static void main(String[] args) {

        Client client = ClientBuilder.newClient();
        String count = client.target("http://localhost:8080/Complaints/resources/complaints/count")
            .request(MediaType.TEXT_PLAIN)
            .get(String.class);

        System.out.println("Count: " + count);

        // Get all complaints
        String allComplaints = client.target("http://localhost:8080/Complaints/resources/complaints")
            .request(MediaType.APPLICATION_JSON)
            .get(String.class);
        System.out.println("All complaints: " + allComplaints);

        // Get ID
        JSONArray complaints = new JSONArray(allComplaints);
        Integer id = -1;
        for (int i = 0; i < complaints.length(); i++) {
            JSONObject complaint = complaints.getJSONObject(i);
            String status = complaint.getString("status");
            if (status.equals("open")) {
                id = complaint.getInt("id");
                break;
            }
        }
        if (id != -1) {
            // Get a single complaint
            String openedComplaint = client.target("http://localhost:8080/Complaints/resources/complaints/" + id)
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);

            System.out.println("Opened complaint: " + openedComplaint);

            JSONObject complaintJson = new JSONObject(openedComplaint);
            complaintJson.put("status", "closed");

            // Modify existing complaint
            client.target("http://localhost:8080/Complaints/resources/complaints/" + id)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(complaintJson.toString()));

        }

        // Get all opened complaints
        String allOpenedComplaints = client.target("http://localhost:8080/Complaints/resources/complaints?status=open")
            .request(MediaType.APPLICATION_JSON)
            .get(String.class);

        System.out.println("Opened complaints: " + allOpenedComplaints);

        client.close();
    }
}