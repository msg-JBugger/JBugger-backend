package com.example.demo.controller;

import com.example.demo.bug_api_calls.*;
import com.example.demo.service.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bug")
public class BugController {
    private final BugService bugService;

    @Autowired
    public BugController(BugService bugService) {
        this.bugService = bugService;
    }

    // TODO: Remove this endpoint
    @GetMapping("/demo")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from secured endpoint!");
    }

    @GetMapping("/{bugId}")
    public ResponseEntity<BugContent> getBugById(@PathVariable long bugId) {
        var bugGetByIdResponse = bugService.getBugById(bugId);
        return bugGetByIdResponse.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<BugSearchResponse> searchBugs(@RequestBody BugSearchRequest request) {
        var bugSearchResponse = bugService.searchBugs(request);
        return ResponseEntity.ok(bugSearchResponse);
    }

    @PostMapping("/add")
    public ResponseEntity<BugContent> addBug(@RequestParam String username, @RequestBody BugAddRequest request) {
        var bugAddResponse = bugService.addBug(username, request);
        return ResponseEntity.ok(bugAddResponse);
    }

    @PutMapping("/update/{bugId}")
    public ResponseEntity<BugContent> updateBug(@PathVariable long bugId, @RequestBody BugUpdateRequest request) {
        var bugUpdateResponse = bugService.updateBug(bugId, request);
        return ResponseEntity.ok(bugUpdateResponse);
    }

    @PatchMapping("/close/{bugId}")
    public ResponseEntity<BugContent> closeBug(@PathVariable long bugId) {
        var bugCloseResponse = bugService.closeBug(bugId);
        return ResponseEntity.ok(bugCloseResponse);
    }

    @PatchMapping("/statusUpdate/{bugId}")
    public ResponseEntity<BugContent> updateBugStatus(@PathVariable long bugId, @RequestBody BugStatusUpdateRequest request) {
        var bugStatusUpdateResponse = bugService.updateBugStatus(bugId, request);
        return ResponseEntity.ok(bugStatusUpdateResponse);
    }

    @PostMapping("/addAttachment/{bugId}")
    public ResponseEntity<BugContentAttachment> addAttachment(@PathVariable long bugId, @RequestBody BugAddAttachmentRequest request) {
        var bugAddAttachmentResponse = bugService.addAttachment(bugId, request);
        return ResponseEntity.ok(bugAddAttachmentResponse);
    }


    @DeleteMapping("/deleteAttachment/{bugId}/{attachmentId}")
    public ResponseEntity<Void> deleteAttachment(@PathVariable long bugId, @PathVariable long attachmentId) {
        bugService.deleteAttachment(bugId, attachmentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}