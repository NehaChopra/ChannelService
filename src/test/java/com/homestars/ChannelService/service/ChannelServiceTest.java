package com.homestars.ChannelService.service;

import com.homestars.ChannelService.dao.Channel;
import com.homestars.ChannelService.model.ChannelResponse;
import com.homestars.ChannelService.repository.ChannelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 *
 * @author nchopra
 *
 */
public class ChannelServiceTest {

	@Mock
	private ChannelRepository channelRepository;

	@InjectMocks
	private ChannelService channelService = Mockito.spy(new ChannelService());

	@Mock
	private TransactionTemplate jpaTransactionTemplate;


	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testLookUpUserBasedOnChannelName(){
		Optional<Channel> channel = Optional.of(Channel.builder().channelName("channelName").build());
		Mockito.when(channelRepository.findByChannelName(Mockito.eq("channelName"))).thenReturn(channel);
		Mockito.when(jpaTransactionTemplate.execute(Mockito.any())).thenReturn(channel);
		ChannelResponse result = channelService.lookupChannel(com.homestars.ChannelService.model.Channel.builder().channelName("channelName").build());

		assertNotNull(result);
//		assertEquals(result.getData()(), channel.get().);
	}

	@Test
	public void testLookUpUserBasedwithMissingUsername(){
		Mockito.when(channelRepository.findByChannelName(Mockito.eq(""))).thenReturn(null);
		Mockito.when(jpaTransactionTemplate.execute(Mockito.any())).thenReturn(null);
		ChannelResponse<com.homestars.ChannelService.dao.Channel> result = channelService.lookupChannel(com.homestars.ChannelService.model.Channel.builder().channelName("").build());
		assertNull(result);
	}

}
