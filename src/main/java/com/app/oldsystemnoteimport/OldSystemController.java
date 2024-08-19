package com.app.oldsystemnoteimport;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class OldSystemController {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final List<Client> CLIENTS = new ArrayList<>();
    static {
        CLIENTS.add(new Client("vhh4", "01588E84-D45A-EB98-F47F-716073A4F1EF", "Ne", "Abr", "INACTIVE",
                LocalDate.parse("1999-10-15", DATE_FORMATTER),
                LocalDateTime.parse("2021-11-15 11:51:59", DATE_TIME_FORMATTER)));
    }

    private static final Map<String, List<Note>> NOTES = new HashMap<>();
    static {
        List<Note> clientNotes = new ArrayList<>();
        clientNotes.add(new Note("20CBCEDA-3764-7F20-0BB6-4D6DD46BA9F8", "C5DCAA49-ADE5-E65C-B776-3F6D7B5F2055",
                LocalDateTime.parse("2021-09-16 12:02:26", DATE_TIME_FORMATTER),
                "p.vasya",
                LocalDateTime.parse("2021-11-15 11:51:59", DATE_TIME_FORMATTER),
                "Patient Care Coordinator, reached out to patient caregiver is still in the hospital."));
        NOTES.put("01588E84-D45A-EB98-F47F-716073A4F1EF", clientNotes);
    }

    @PostMapping("/clients")
    public List<Client> getClients() {
        return CLIENTS;
    }

    @PostMapping("/notes")
    public List<Note> getNotes(@RequestBody NotesRequestPayload payload) {
        return NOTES.getOrDefault(payload.getClientGuid(), new ArrayList<>());
    }

    static class Client {
        private String agency;
        private String guid;
        private String firstName;
        private String lastName;
        private String status;
        private LocalDate dob;
        private LocalDateTime createdDateTime;

        public Client(String agency, String guid, String firstName, String lastName, String status, LocalDate dob, LocalDateTime createdDateTime) {
            this.agency = agency;
            this.guid = guid;
            this.firstName = firstName;
            this.lastName = lastName;
            this.status = status;
            this.dob = dob;
            this.createdDateTime = createdDateTime;
        }

        public String getAgency() { return agency; }
        public void setAgency(String agency) { this.agency = agency; }
        public String getGuid() { return guid; }
        public void setGuid(String guid) { this.guid = guid; }
        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }
        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public LocalDate getDob() { return dob; }
        public void setDob(LocalDate dob) { this.dob = dob; }
        public LocalDateTime getCreatedDateTime() { return createdDateTime; }
        public void setCreatedDateTime(LocalDateTime createdDateTime) { this.createdDateTime = createdDateTime; }
    }

    static class Note {
        private String guid;
        private String clientGuid;
        private LocalDateTime datetime;
        private String loggedUser;
        private LocalDateTime modifiedDateTime;
        private String comments;

        public Note(String guid, String clientGuid, LocalDateTime datetime, String loggedUser, LocalDateTime modifiedDateTime, String comments) {
            this.guid = guid;
            this.clientGuid = clientGuid;
            this.datetime = datetime;
            this.loggedUser = loggedUser;
            this.modifiedDateTime = modifiedDateTime;
            this.comments = comments;
        }

        public String getGuid() { return guid; }
        public void setGuid(String guid) { this.guid = guid; }
        public String getClientGuid() { return clientGuid; }
        public void setClientGuid(String clientGuid) { this.clientGuid = clientGuid; }
        public LocalDateTime getDatetime() { return datetime; }
        public void setDatetime(LocalDateTime datetime) { this.datetime = datetime; }
        public String getLoggedUser() { return loggedUser; }
        public void setLoggedUser(String loggedUser) { this.loggedUser = loggedUser; }
        public LocalDateTime getModifiedDateTime() { return modifiedDateTime; }
        public void setModifiedDateTime(LocalDateTime modifiedDateTime) { this.modifiedDateTime = modifiedDateTime; }
        public String getComments() { return comments; }
        public void setComments(String comments) { this.comments = comments; }
    }

    static class NotesRequestPayload {
        private String agency;
        private String clientGuid;
        private String dateFrom;
        private String dateTo;

        public String getAgency() { return agency; }
        public void setAgency(String agency) { this.agency = agency; }
        public String getClientGuid() { return clientGuid; }
        public void setClientGuid(String clientGuid) { this.clientGuid = clientGuid; }
        public String getDateFrom() { return dateFrom; }
        public void setDateFrom(String dateFrom) { this.dateFrom = dateFrom; }
        public String getDateTo() { return dateTo; }
        public void setDateTo(String dateTo) { this.dateTo = dateTo; }
    }
}
