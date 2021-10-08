package com.homestars.ChannelService.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 *
 * @author nchopra
 *
 */
@Entity
@Table(name = "channel")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Channel implements Serializable {

	private static final long serialVersionUID = -1763574516474353414L;

	@Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "channel_name")
	private String channelName;

	@Column(name = "status")
	private Boolean status;

	@Column(name = "modified_ts")
	private Date modifiedTs;

	@Column(name = "created_ts")
	private Date createdTs;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getchannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getModifiedTs() {
		return modifiedTs;
	}

	public void setModifiedTs(Date modifiedTs) {
		this.modifiedTs = modifiedTs;
	}

	public Date getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}

}
