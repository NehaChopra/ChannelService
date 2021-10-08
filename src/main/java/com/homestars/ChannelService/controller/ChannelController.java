package com.homestars.ChannelService.controller;

import com.homestars.ChannelService.model.Channel;
import com.homestars.ChannelService.model.ChannelListResponse;
import com.homestars.ChannelService.model.ChannelResponse;
import com.homestars.ChannelService.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author nchopra
 *
 */
@RestController
public class ChannelController {

	@Autowired
	private ChannelService channelService;

	@RequestMapping(value = "/channels", method = { RequestMethod.POST })
	public ResponseEntity<?> createUser(@RequestBody Channel userinput ) {
		ChannelResponse<com.homestars.ChannelService.dao.Channel> user = channelService.create(userinput);
		return ResponseEntity.ok(user);
	}

	// list of available channels
	@RequestMapping(value = "/channels", method = { RequestMethod.GET })
	public ResponseEntity<?> getUser() {
		ChannelListResponse<com.homestars.ChannelService.dao.Channel> users = channelService.getChannels();
		return ResponseEntity.ok(users);
	}

	//lookup of the channel based on channelName
	@RequestMapping(value = "/channel", method = { RequestMethod.GET })
	public ResponseEntity<?> lookupChannel(@RequestBody Channel userinput ) {
		ChannelResponse<com.homestars.ChannelService.dao.Channel> user = channelService.lookupChannel(userinput);
		return ResponseEntity.ok(user);
	}

}
