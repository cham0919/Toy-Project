package com.wcp.token.refresh;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Index;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import com.wcp.WCPTable.RefreshTokenTable;



@Entity
@Table(name=RefreshTokenTable.TABLE_NAME)
@Data
@Accessors(chain = true)
public class RefreshToken {
	@Id
	@Column(name=RefreshTokenTable.PK)
	private Long id;

	@Column(name=RefreshTokenTable.REFRESH_TOKEN, nullable=false, unique=true)
	private String refreshToken;

	@Column(name=RefreshTokenTable.ACCESS_TOKEN,  nullable=false)
	private String accessToken;

//	@Index(name="idx_expiration_date", columnNames="expiration_date")
	@Column(name=RefreshTokenTable.EXPIRATION_DATE, nullable=false)
	private Date expirationDate;


/*	public RefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
		this.expirationDate = (Date) JwtService.getInstance().getClaim(refreshToken, "exp");
	}*/
}
