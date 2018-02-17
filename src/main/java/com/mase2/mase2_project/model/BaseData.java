package com.mase2.mase2_project.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;



/**
 * The persistent class for the base_data database table.
 * 
 */
@Entity
@Table(name="base_data")
@NamedQuery(name="BaseData.findAll", query="SELECT b FROM BaseData b")
public class BaseData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="base_data_id")
	private int baseDataId;

	@Column(name="cell_id")
	private int cellId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_time")
	private Date dateTime;

	private int duration;

	@Column(name="hier3_id")
	private BigDecimal hier3Id;

	@Column(name="hier32_id")
	private BigDecimal hier32Id;

	@Column(name="hier321_id")
	private BigDecimal hier321Id;

	private BigDecimal imsi;

	@Column(name="ne_version")
	private String neVersion;

	//bi-directional many-to-one association to EventCause
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="cause_code", referencedColumnName="event_code"),
		@JoinColumn(name="event_id", referencedColumnName="event_id")
		})
	private EventCause eventCause;

	//bi-directional many-to-one association to Ue
	@ManyToOne
	@JoinColumn(name="ue_type")
	private Ue ue;

	//bi-directional many-to-one association to MccMnc
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="market", referencedColumnName="mcc"),
		@JoinColumn(name="operator", referencedColumnName="mnc")
		})
	private MccMnc mccMnc;

	//bi-directional many-to-one association to FailureClass
	@ManyToOne
	@JoinColumn(name="failure_class")
	private FailureClass failureClassBean;

	public BaseData() {
	}

	public int getBaseDataId() {
		return this.baseDataId;
	}

	public void setBaseDataId(int baseDataId) {
		this.baseDataId = baseDataId;
	}

	public int getCellId() {
		return this.cellId;
	}

	public void setCellId(int cellId) {
		this.cellId = cellId;
	}

	public Date getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public BigDecimal getHier3Id() {
		return this.hier3Id;
	}

	public void setHier3Id(BigDecimal hier3Id) {
		this.hier3Id = hier3Id;
	}

	public BigDecimal getHier32Id() {
		return this.hier32Id;
	}

	public void setHier32Id(BigDecimal hier32Id) {
		this.hier32Id = hier32Id;
	}

	public BigDecimal getHier321Id() {
		return this.hier321Id;
	}

	public void setHier321Id(BigDecimal hier321Id) {
		this.hier321Id = hier321Id;
	}

	public BigDecimal getImsi() {
		return this.imsi;
	}

	public void setImsi(BigDecimal imsi) {
		this.imsi = imsi;
	}

	public String getNeVersion() {
		return this.neVersion;
	}

	public void setNeVersion(String neVersion) {
		this.neVersion = neVersion;
	}

	public EventCause getEventCause() {
		return this.eventCause;
	}

	public void setEventCause(EventCause eventCause) {
		this.eventCause = eventCause;
	}

	public Ue getUe() {
		return this.ue;
	}

	public void setUe(Ue ue) {
		this.ue = ue;
	}

	public MccMnc getMccMnc() {
		return this.mccMnc;
	}

	public void setMccMnc(MccMnc mccMnc) {
		this.mccMnc = mccMnc;
	}

	public FailureClass getFailureClassBean() {
		return this.failureClassBean;
	}

	public void setFailureClassBean(FailureClass failureClassBean) {
		this.failureClassBean = failureClassBean;
	}

	public void createRow(ArrayList<String> cells, EventCause eventCauseRow, FailureClass failureClassRow, Ue ueRow, MccMnc mccMncRow) {
		try {
			this.setDateTime(new SimpleDateFormat("dd/MM/yy HH:mm").parse(cells.get(0)));
		} catch (ParseException e) {
			this.setDateTime(new Date());
			e.printStackTrace();
		}
		this.setEventCause(eventCauseRow);
		this.setFailureClassBean(failureClassRow);
		this.setMccMnc(mccMncRow);
		this.setUe(ueRow);
		this.setCellId(Integer.parseInt(cells.get(6)));
		this.setDuration(Integer.parseInt(cells.get(7)));
		this.setNeVersion(cells.get(9));
		this.setImsi(new BigDecimal(cells.get(10)));
		this.setHier3Id(new BigDecimal(cells.get(11)));
		this.setHier32Id(new BigDecimal(cells.get(12)));
		this.setHier321Id(new BigDecimal(cells.get(13)));
		
	}

}