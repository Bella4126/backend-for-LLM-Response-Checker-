
package com.Singh.SpringAIDemo.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController   // kyuk wee are acceptin as well as sending response in JSON format
@RequestMapping("/api/ollama")
@CrossOrigin("*")  //local host k port ko connect kra h

public class OllamaController {
    

    private ChatClient chatClient;// Ye ek step up h chat model ka isse hum meta dat bhi ad kr skte h aur bhi extra otins deta h
    // private  OpenAiChatModel chatModel;  // built in openAichatModel se obj bnaya , 
public  OllamaController(OllamaChatModel chatModel){  //Dependedncy INjection using Contructor
    this.chatClient=ChatClient.create(chatModel);
}
  @GetMapping("/{message}")  // yha user ka message recive hua 
public ResponseEntity<String> getAnswer(@PathVariable String message){  // yha path variable se accept hua 
    // String response =chatModel.call(message); // call lgayi , messgae pass krte huye 

ChatResponse chatResponse =chatClient.prompt(message)// String to Chat response change kia h 
                           .call()
                            .chatResponse();
                        //    .content();// isse humne build kr ah ki ye return mei ek string de
                    String response = chatResponse.getResult().getOutput().getText();   // phirse build kra  h
    return ResponseEntity.ok(response);  /// reponse retunr krdia
}

       }
    
