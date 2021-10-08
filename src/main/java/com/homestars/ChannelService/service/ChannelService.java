package com.homestars.ChannelService.service;

import com.homestars.ChannelService.exception.APIException;
import com.homestars.ChannelService.exception.BadRequestError;
import com.homestars.ChannelService.exception.IntenalServerError;
import com.homestars.ChannelService.exception.NotFoundError;
import com.homestars.ChannelService.model.Channel;
import com.homestars.ChannelService.model.ChannelListResponse;
import com.homestars.ChannelService.model.ChannelResponse;
import com.homestars.ChannelService.repository.ChannelRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.ObjectUtils;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author nchopra
 *
 */
@Service
public class ChannelService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ChannelService.class);

	@Autowired
	private ChannelRepository channelRepository;

	@Autowired
	private TransactionTemplate jpaTransactionTemplate;

	/**
	 * create a Channel
	 *
	 * @return Channel
	 * @param channelInput
	 */
	public ChannelResponse<com.homestars.ChannelService.dao.Channel> create(Channel channelInput) throws APIException {
		ChannelResponse response = null;
		try {
			//validate user
			com.homestars.ChannelService.dao.Channel user = com.homestars.ChannelService.dao.Channel.builder().channelName(channelInput.getChannelName()).build();
			if(!ObjectUtils.isEmpty(user)) {
				response = ChannelResponse.builder().data(channelRepository.save(user)).build();
			}
		}catch(Exception e) {
			LOGGER.error(String.format("Error while creating a user : %s", channelInput.getChannelName()));
			throw new BadRequestError("Bad request");
		}
		return response;
	}

	public ChannelResponse<com.homestars.ChannelService.dao.Channel> lookupChannel(Channel channelInput) {
		ChannelResponse response = null;
		Optional<com.homestars.ChannelService.dao.Channel> channels = null;
		try {
			if(StringUtils.isNotEmpty(channelInput.getChannelName())){
				channels = findByChannelName(channelInput.getChannelName());
			}
		} catch (Exception e) {
			LOGGER.error(String.format("Error while looking up a channel : %s", channelInput.getChannelName()));
			throw new NotFoundError("Not Found");
		}

		if (!ObjectUtils.isEmpty(channels)) {
			response = ChannelResponse.builder().data(channels.get()).build();
		}
		return response;
	}

	/**
	 * lookup a channel by channelName
	 *
	 * @return Optional<Channel>
	 * @param channelName
	 */
	private Optional<com.homestars.ChannelService.dao.Channel> findByChannelName(String channelName){
		ChannelResponse<com.homestars.ChannelService.dao.Channel> response = null;
		Optional<com.homestars.ChannelService.dao.Channel> channels =
				jpaTransactionTemplate.execute(txn -> {
					Optional<com.homestars.ChannelService.dao.Channel> dbChannels = null;
					try {
						dbChannels = channelRepository.findByChannelName(channelName);
						if (ObjectUtils.isEmpty(dbChannels)) {
							LOGGER.error(String.format("Error while looking up a channel by channelName : %s", channelName));
							throw new NotFoundError("Not Found");
						}
						return dbChannels;
					} catch (Exception ex) {
						LOGGER.error(String.format(" Intenal ServerError while looking channel by channelName : %s", channelName));
						throw new IntenalServerError("Internal server error");
					}
				});
		return channels;
	}

	public ChannelListResponse<com.homestars.ChannelService.dao.Channel> getChannels() {
		ChannelListResponse response = null;
		List channels = null;
		try {
			channels = jpaTransactionTemplate.execute(txn -> {
				List<com.homestars.ChannelService.dao.Channel> dbChannels = null;
				try {
					dbChannels = channelRepository.findAll();
					if (ObjectUtils.isEmpty(dbChannels)) {
						LOGGER.error(String.format("No user records found"));
						throw new NotFoundError("Not Found");
					}
					return dbChannels;
				} catch (Exception ex) {
					LOGGER.error(String.format("IntenalServerError while fetching users"));
					throw new IntenalServerError("Internal server error");
				}
			});
		} catch (Exception e) {
			LOGGER.error(String.format("IntenalServerError while fetching users"));
			throw new IntenalServerError("Internal server error");
		}

		if (!ObjectUtils.isEmpty(channels)) {
			response = ChannelListResponse.builder().data(channels).build();
		}
		return response;
	}
}
