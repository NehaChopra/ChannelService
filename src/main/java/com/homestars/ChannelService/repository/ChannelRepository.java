package com.homestars.ChannelService.repository;


import com.homestars.ChannelService.dao.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 
 * @author nchopra
 *
 */
public interface ChannelRepository extends JpaRepository<Channel, Long> {
	
	Optional<Channel> findByChannelName(String channelName);

}